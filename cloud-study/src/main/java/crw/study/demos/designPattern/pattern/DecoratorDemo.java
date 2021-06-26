package crw.study.demos.designPattern.pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 装饰者模式的应用
 */
public class DecoratorDemo {

    private static final Logger logger = LoggerFactory.getLogger(DecoratorDemo.class);

    public static void main(String[] args) {
        Student student = new Student();
        student.study();
        Student decoratorStudent = new DecoratorStudent();

        logger.info("扩展 {}", Thread.currentThread().getName());
        ((DecoratorStudent) decoratorStudent).studyOther();
    }
}

interface StudyLanguage {
    void study();
}


class Student implements StudyLanguage {

    private static final Logger logger = LoggerFactory.getLogger(Student.class);

    public Student() {}

    @Override
    public void study() {
        logger.info("learn chinese");
    }
}


class DecoratorStudent extends Student {

    private static final Logger logger = LoggerFactory.getLogger(DecoratorStudent.class);

    @Override
    public void study() {
        super.study();
        studyOther();
    }

    public void studyOther() {
        logger.info("learn English");
    }
}