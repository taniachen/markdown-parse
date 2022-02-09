# set -e #tells bash to quit on any nonzero exit code

CLASSPATH=lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:./

javac -cp $CLASSPATH *.java

if [ $? != 0 ]
then
    echo "Exiting early!"
    exist 123
fi

java -cp $CLASSPATH org.junit.runner.JUnitCore MarkdownParseTest
# if [ $? != 00 ]
# then
#   echo "Exit code was not zero"
#   exit 1
# fi

# echo "Exit code was zero"
