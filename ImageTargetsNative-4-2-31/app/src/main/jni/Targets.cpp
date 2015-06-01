#include <fstream>
#include <sstream>
#include <string>
#include <vector>
#include <iostream>
#include <istream>
#include "Targets.h"

#include <android/log.h>
#include <android/asset_manager.h>
#include <jni.h>
#include <android/asset_manager_jni.h>

#define  LOG_TAG    "testjni"
#define  ALOG(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)

//global List for class
std::vector<std::string> listOfElements;

//Constructor to convert jobjectArray to stringArray
Targets::Targets( JNIEnv *env, jobjectArray stringArray )
{

    ALOG("Attempting to get target names..");

    if (stringArray == NULL)
    ALOG("Did not get target names!");
    
    else
    {
        int stringCount = env->GetArrayLength(stringArray);


        for (int i=0; i<stringCount; i++) 
        {
            jstring string = (jstring) env->GetObjectArrayElement(stringArray, i);
            const char *rawString = env->GetStringUTFChars(string, 0);
            std::string tempString(rawString);
            listOfElements.push_back(tempString);
        }
    }
}

//empty constructor for global initialization
Targets::Targets()
{

}

//returns the list of elements in the vector
std::vector<std::string > Targets::getTargetListElements()
{
    return listOfElements;
}

//returns the size of the vector
int Targets::getTargetListSize()
{
    return listOfElements.size();
}

//checks if the element exists in the list
bool Targets::hasElement(std::string sampleString)
{
    if (std::find(listOfElements.begin(), listOfElements.end(), sampleString) != listOfElements.end())
            return true;

        return false;
}

//gets the index of the element by traversing and comparing
int Targets::getIndexOf(std::string sampleString)
{
        for(int i = 0; i < listOfElements.size(); i++)
        {
            if(listOfElements.at(i) == sampleString)
                return i;
        }

            return -999;
}

std::string Targets::getTargetNameAtIndex(int index)
{   
    return listOfElements.at(index);
}