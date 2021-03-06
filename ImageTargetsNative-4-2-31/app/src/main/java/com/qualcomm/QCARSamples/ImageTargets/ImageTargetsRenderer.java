/*===============================================================================
Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved.

Vuforia is a trademark of QUALCOMM Incorporated, registered in the United States 
and other countries. Trademarks of QUALCOMM Incorporated are used with permission.
===============================================================================*/

package com.qualcomm.QCARSamples.ImageTargets;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.qualcomm.QCAR.QCAR;
import com.qualcomm.vuforia.State;


/** The renderer class for the ImageTargets sample. */
public class ImageTargetsRenderer implements GLSurfaceView.Renderer
{
    public boolean mIsActive = false;
    
    /** Reference to main activity **/
    public ImageTargets mActivity;

    /** Native function for initializing the renderer. */
    public native void initRendering();
    
    
    /** Native function to update the renderer. */
    public native void updateRendering(int width, int height);
    
    
    /** Called when the surface is created or recreated. */
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        DebugLog.LOGD("GLRenderer::onSurfaceCreated");
        
        // Call native function to initialize rendering:
        initRendering();
        
        // Call Vuforia function to (re)initialize rendering after first use
        // or after OpenGL ES context was lost (e.g. after onPause/onResume):
        QCAR.onSurfaceCreated();
    }
    
    
    /** Called when the surface changed size. */
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        DebugLog.LOGD("GLRenderer::onSurfaceChanged");
        
        // Call native function to update rendering when render surface
        // parameters have changed:
        updateRendering(width, height);
        
        // Call Vuforia function to handle render surface size changes:
        QCAR.onSurfaceChanged(width, height);
    }
    
    
    /** The native render function. */
    public native void renderFrame();
    
    /** Called to draw the current frame. */
    public void onDrawFrame(GL10 gl)
    {
        if (!mIsActive)
            return;

        // Update render view (projection matrix and viewport) if needed:
        mActivity.updateRenderView();
        
        // Call our native function to render content
        Log.e("TAG", "About to render frame...!");
        renderFrame();
    }

    public void showDialogBox(String s)
    {
        Log.e("TAG", "About to show dialog box");
        mActivity.showDialogBox(s);
    }

    public void hideDialogBox()
    {
        Log.e("TAG", "About to hide dialog box");
        mActivity.hideDialogBox();
    }
}
