echo "insert filename"
read filename
java -jar mypallene2c.jar $filename
clang-format -style=google "$filename.c" -i && clang "$filename.c" -S -O3 -emit-llvm -o - >$filename.ll
clang "$filename.c"
./a.out
