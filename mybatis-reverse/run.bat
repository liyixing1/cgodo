java -Dfile.encoding=UTF-8 -cp .;com.cgodo-0.0.2-SNAPSHOT.jar;mybatis-generator-core-1.3.2/mybatis-generator-core-1.3.2.jar org.mybatis.generator.api.ShellRunner -configfile serverGeneratorConfig.xml -overwrite

java -Dfile.encoding=UTF-8 -cp .;com.cgodo-0.0.2-SNAPSHOT.jar;mybatis-generator-core-1.3.2/mybatis-generator-core-1.3.2.jar org.mybatis.generator.api.ShellRunner -configfile memberGeneratorConfig.xml -overwrite

java -Dfile.encoding=UTF-8 -cp .;com.cgodo-0.0.2-SNAPSHOT.jar;mybatis-generator-core-1.3.2/mybatis-generator-core-1.3.2.jar org.mybatis.generator.api.ShellRunner -configfile counterGeneratorConfig.xml -overwrite