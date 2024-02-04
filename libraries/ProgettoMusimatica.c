#include <conio.h>
#include <jni.h>
#include <stdlib.h>

JNIEXPORT jint JNICALL
Java_com_v1nc3nz0_musimathics_io_NativeC_getch(JNIEnv *env, jobject obj) 
{
    return getch();
}

JNIEXPORT jchar JNICALL
Java_com_v1nc3nz0_musimathics_io_NativeC_system(JNIEnv *env, jobject obj,jstring command) 
{
    const char *cmd = (*env)->GetStringUTFChars(env, command, 0);
    system(cmd);
    (*env)->ReleaseStringUTFChars(env, command, cmd);
}