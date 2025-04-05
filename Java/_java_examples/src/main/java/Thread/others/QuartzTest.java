/*
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 */

package com.gqz.others;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author ganquanzhong
 * @ClassName: QuartzTest 学习
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月10日 下午4:59:31
 */
public class QuartzTest {

  public static void main(String[] args) throws Exception {

    QuartzTest example = new QuartzTest();
    example.run();

  }

  public void run() throws Exception {


    // First we must get a reference to a scheduler
    // 1、创建Scheduler的工厂
    SchedulerFactory sf = new StdSchedulerFactory();
    //2、从工厂中获取调度器
    Scheduler sched = sf.getScheduler();


    // computer a time that is on the next round minute
    //时间 下一次运行
    Date runTime = evenMinuteDate(new Date());


    // define the job and tie it to our HelloJob class
    //3、创建JobDetail
    JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

    // Trigger the job to run on the next round minute
    //4、触发器
    Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

    // Tell quartz to schedule the job using our trigger
    //5、注册任务和触发条件
    sched.scheduleJob(job, trigger);

    // Start up the scheduler (nothing can actually run until the
    // scheduler has been started)
    //6、启动
    sched.start();


    // wait long enough so that the scheduler as an opportunity to
    // run the job!
    try {
      // wait 65 seconds to show job
      Thread.sleep(65L * 1000L);
      // executing...
    } catch (Exception e) {
      //
    }

    // shut down the scheduler
    sched.shutdown(true);
  }

}
