#include <fstream>
#include <sstream>
#include <string>
#include <vector>
#include <iostream>
#include <istream>

#include <android/log.h>
#include <android/asset_manager.h>
#include <jni.h>
#include <android/asset_manager_jni.h>

class Targets{
    public:
        Targets( JNIEnv*, jobjectArray );

        Targets();

        std::vector<std::string> getTargetListElements();

        bool hasElement(std::string);

        int getIndexOf(std::string);

        int getTargetListSize();

        std::string getTargetNameAtIndex(int);  
};