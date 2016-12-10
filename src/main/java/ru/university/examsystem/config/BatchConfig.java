package ru.university.examsystem.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ru.university.examsystem.dao.task.TaskRepository;
import ru.university.examsystem.dao.user.UserRepository;
import ru.university.examsystem.entity.Task;
import ru.university.examsystem.entity.User;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public FlatFileItemReader<User> userReader() {
        FlatFileItemReader<User> userReader = new FlatFileItemReader<>();
        userReader.setResource(new ClassPathResource("data/user.csv"));
        userReader.setLineMapper(
                new DefaultLineMapper<User>() {
                    {
                        setLineTokenizer(new DelimitedLineTokenizer() {{
                            setNames(new String[]{"name", "surname", "username", "password", "role"});
                        }});
                        setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
                            setTargetType(User.class);
                        }});
                    }
                }
        );

        return userReader;
    }

    @Bean
    public RepositoryItemWriter<User> userWriter() {
        RepositoryItemWriter<User> userWriter = new RepositoryItemWriter<>();
        userWriter.setRepository(userRepository);
        userWriter.setMethodName("save");

        return userWriter;
    }

    @Bean
    public FlatFileItemReader<Task> taskReader() {
        FlatFileItemReader<Task> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("data/task.csv"));
        reader.setLineMapper(
                new DefaultLineMapper<Task>() {
                    {
                        setLineTokenizer(new DelimitedLineTokenizer() {
                            {
                                setNames(new String[]{"number", "text"});
                            }
                        });
                        setFieldSetMapper(new BeanWrapperFieldSetMapper<Task>() {{
                            setTargetType(Task.class);
                        }});
                    }
                }
        );

        return reader;
    }

    @Bean
    public RepositoryItemWriter<Task> taskWriter() {
        RepositoryItemWriter<Task> taskWriter = new RepositoryItemWriter<>();
        taskWriter.setRepository(taskRepository);
        taskWriter.setMethodName("save");

        return taskWriter;
    }

    @Bean
    public Job initImport() {
        return jobBuilderFactory.get("init import")
                .incrementer(new RunIdIncrementer())
                .flow(importUsers())
                .next(importTasks())
                .end()
                .build();
    }

    @Bean
    public Step importUsers() {
        return stepBuilderFactory.get("import users")
                .<User, User>chunk(10)
                .reader(userReader())
                .writer(userWriter())
                .build();
    }

    @Bean
    public Step importTasks() {
        return stepBuilderFactory.get("import tasks")
                .<Task, Task>chunk(10)
                .reader(taskReader())
                .writer(taskWriter())
                .build();
    }

}
