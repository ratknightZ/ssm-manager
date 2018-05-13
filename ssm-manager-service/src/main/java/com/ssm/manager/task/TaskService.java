package com.ssm.manager.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: chen
 * @Date: Created in 20:26 2018/5/13
 */
@Service
public class TaskService{

    private boolean flag = true;

    @Async
    public void task1(){
        while (flag) {
            System.out.println("任务1...");
        }
    }

    @Async
    public void task2(){
        while (flag) {
            System.out.println("任务2...");
        }
    }

    @Async
    public void task3(){
        while (flag){
            System.out.println("任务3...");
        }
    }

    public void close(){
        flag = false;
    }
}
