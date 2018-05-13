package com.ssm.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: chen
 * @Date: Created in 19:53 2018/5/13
 */
@EnableAsync
public class TaskStart{

    @Autowired
    private TaskService taskService;

    public void taskStart(){
        taskService.task1();
        taskService.task2();
        taskService.task3();
    }

    public void taskEnd(){
        taskService.close();
    }
}
