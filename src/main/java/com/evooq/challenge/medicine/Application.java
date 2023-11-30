package com.evooq.challenge.medicine;

import com.evooq.challenge.medicine.runner.TaskRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private TaskRunnable taskRunnable;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

		/* Note:
        When building or running tests, profile is set to "test"
        and a dummy bean user here to prevent application execution, with empty args.
        */
        taskRunnable.run(args);
    }

}
