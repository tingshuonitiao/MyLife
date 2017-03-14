package com.mylife.tsnt.manager;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public class ThreadManager {
    private static ExecutorService EXECUTORS_INSTANCE;

    private static Executor getExecutor() {
        if (EXECUTORS_INSTANCE == null) {
            synchronized (ThreadManager.class) {
                if (EXECUTORS_INSTANCE == null) {
                    EXECUTORS_INSTANCE = Executors.newFixedThreadPool(6);
                }
            }
        }
        return EXECUTORS_INSTANCE;
    }

    public static void runOnThread(Runnable runnable) {
        getExecutor().execute(runnable);
    }
}
