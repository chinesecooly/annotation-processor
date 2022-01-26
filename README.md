# 说明
一个简单的注解处理demo，它的作用是根据ToString注解自动生成toString方法
# 运行步骤
本项目没有使用maven，而是直接使用javac
- 第一步：创建一个与src同级的target目录用于放置类文件
- 第二步：编译注解处理器

```bash
javac -classpath target -sourcepath src -s target src/cn/superstallion/AnnotationProcessor.java
```
- 第三步：运行注解处理器
```bash
javac -classpath target -sourcepath src -s target -d src -processor cn.superstallion.AnnotationProcessor src/cn/superstallion/Student.java src/cn/superstallion/Teacher.java
```
# 总结
此项目不仅了解了注解处理器的创建和执行流程而且还练习了javac的使用