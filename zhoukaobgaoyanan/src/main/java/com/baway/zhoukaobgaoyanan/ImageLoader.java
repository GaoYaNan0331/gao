package com.baway.zhoukaobgaoyanan;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * data:2017/4/10
 * author:高亚男(Administrator)
 * function:
 */
public class ImageLoader {
    public static DisplayImageOptions getoptions(int ImageId){
        DisplayImageOptions options=new DisplayImageOptions.Builder().showImageOnLoading(ImageId).showImageForEmptyUri(ImageId).build();
        return options;
    }
}
