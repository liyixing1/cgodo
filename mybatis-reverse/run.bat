java -Dfile.encoding=UTF-8 -cp .;com.cgodo-0.0.10-SNAPSHOT.jar;mybatis-generator-core-1.3.2/mybatis-generator-core-1.3.2.jar com.cgodo.mybatis.generator.CgodoShellRunner -configfile serverGeneratorConfig.xml -overwrite

java -Dfile.encoding=UTF-8 -cp .;com.cgodo-0.0.10-SNAPSHOT.jar;mybatis-generator-core-1.3.2/mybatis-generator-core-1.3.2.jar com.cgodo.mybatis.generator.CgodoShellRunner -configfile memberGeneratorConfig.xml -overwrite

java -Dfile.encoding=UTF-8 -cp .;com.cgodo-0.0.10-SNAPSHOT.jar;mybatis-generator-core-1.3.2/mybatis-generator-core-1.3.2.jar com.cgodo.mybatis.generator.CgodoShellRunner -configfile counterGeneratorConfig.xml -overwrite