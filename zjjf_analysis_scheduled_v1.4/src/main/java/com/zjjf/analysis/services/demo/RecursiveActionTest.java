package com.zjjf.analysis.services.demo;

import java.util.concurrent.RecursiveAction;

public class RecursiveActionTest extends RecursiveAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 每个"小任务"最多只打印20个数
	private static final int MAX = 20;

	private int start;
	private int end;

	RecursiveActionTest(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		// 当end-start的值小于MAX时候，开始打印
		if ((end - start) < MAX) {
			for (int i = start; i < end; i++) {
				System.out.println(Thread.currentThread().getName() + "的i值:" + i);
			}
		} else {
			// 将大任务分解成两个小任务
			int middle = (start + end) / 2;
			RecursiveActionTest left = new RecursiveActionTest(start, middle);
			RecursiveActionTest right = new RecursiveActionTest(middle, end);
			// 并行执行两个小任务
			left.fork();
			right.fork();
		}
	}
}
