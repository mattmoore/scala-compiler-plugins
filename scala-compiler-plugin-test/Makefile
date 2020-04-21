compile-plugin:
	@scalac \
		-cp ../scala-compiler-plugin-example/target/scala-2.13/sbt-1.0/scala-compiler-plugin-example-0.0.1-SNAPSHOT.jar \
		-Xplugin:../scala-compiler-plugin-example/target/scala-2.13/sbt-1.0/scala-compiler-plugin-example-0.0.1-SNAPSHOT.jar \
		*.scala

compile-no-plugin:
	@scalac \
		*.scala

run:
	@scala Test

clean:
	@rm -rf *.class

phases:
	@scalac \
		-cp ../scala-compiler-plugin-example/target/scala-2.13/sbt-1.0/scala-compiler-plugin-example-0.0.1-SNAPSHOT.jar \
		-Xplugin:../scala-compiler-plugin-example/target/scala-2.13/sbt-1.0/scala-compiler-plugin-example-0.0.1-SNAPSHOT.jar \
		-Xshow-phases \
		*.scala

plugins:
	@scalac \
		-cp ../scala-compiler-plugin-example/target/scala-2.13/sbt-1.0/scala-compiler-plugin-example-0.0.1-SNAPSHOT.jar \
		-Xplugin:../scala-compiler-plugin-example/target/scala-2.13/sbt-1.0/scala-compiler-plugin-example-0.0.1-SNAPSHOT.jar \
		-Xplugin-list \
		*.scala
