#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "me_serce_nativelib_NativeLib.h"

JNIEXPORT void JNICALL Java_me_serce_nativelib_NativeLib_goodNative(JNIEnv *env, jclass clz, jint size) {
  char* arr = (char*)malloc(size);
  for(int i = 0; i < size; i++)
    arr[i] = i;
  free(arr);
}

JNIEXPORT void JNICALL Java_me_serce_nativelib_NativeLib_badNative(JNIEnv *env, jclass clz, jint size) {
  char* arr = (char*)malloc(size);
  for(int i = 0; i < size; i++)
    arr[i] = i;
}
