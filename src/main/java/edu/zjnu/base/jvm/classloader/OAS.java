package edu.zjnu.base.jvm.classloader;

/**
 * @description: OAS
 * @author: 杨海波
 * @date: 2021-10-03
 **/
public class OAS {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        double salary = 25000.0d;
        double realSalary = 0.0d;

        String classpath = "/Users/setsunayang/Documents/learnspace/learning/target/classes/edu/zjnu/base/jvm/classloader/SalaryCalerV2.class";
        SalaryClassLoader salaryClassLoader = new SalaryClassLoader(classpath);
        Class<?> classLoaderClass = salaryClassLoader.findClass("edu.zjnu.base.jvm.classloader.SalaryCalerV2");

        while (true) {
            realSalary = new SalaryCaler().cla(salary);
            System.out.println("实际到手薪资:" + realSalary);
            Thread.sleep(5000);
        }
    }
}
