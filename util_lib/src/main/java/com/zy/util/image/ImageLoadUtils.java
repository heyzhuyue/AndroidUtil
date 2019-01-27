package com.zy.util.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.io.File;

/**
 * Created by zy on 2017/8/22.
 */

public class ImageLoadUtils {


    private Builder builder;

    /**
     * 初始化图片加载Utils
     *
     * @param context 上下文
     * @return
     */
    public static Builder init(Context context) {
        Builder builder = new Builder(context);
        return builder;
    }

    private ImageLoadUtils(Builder builder) {
        this.builder = builder;
    }

    public static class Builder {

        /**
         * 上下文
         */
        public Context context;

        /**
         * 图片BitmapDrawable
         */
        public int drawable;

        /**
         * 网络图片Url地址
         */
        String url;

        /**
         * 拍照返回Uri地址
         */
        public Uri uri;

        /**
         * 本地图片路径
         */
        String photoFilePath;

        /**
         * 是否需要缓存
         */
        boolean isNeedCache = true;

        /**
         * 记载图片回调
         */
        ImageLoadCallBack imageLoadCallBack;

        /**
         * 图片
         */
        ImageView imageView;

        /**
         * 是否圆形图标
         */
        boolean isCircle = false;

        /**
         * 是否圆角
         */
        boolean isRaadius = false;

        /**
         * 圆角度数
         */
        int radius = 4;

        Builder(Context context) {
            this.context = context;
        }

        public Builder setDrawable(int drawable) {
            this.drawable = drawable;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setUri(Uri uri) {
            this.uri = uri;
            return this;
        }

        public Builder setPhotoFilePath(String photoFilePath) {
            this.photoFilePath = photoFilePath;
            return this;
        }

        public Builder setCircle(boolean circle) {
            isCircle = circle;
            return this;
        }

        public Builder setRaadius(boolean raadius) {
            isRaadius = raadius;
            return this;
        }

        public Builder setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        public Builder setNeedCache(boolean needCache) {
            isNeedCache = needCache;
            return this;
        }

        public Builder setImageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder setImageLoadCallBack(ImageLoadCallBack imageLoadCallBack) {
            this.imageLoadCallBack = imageLoadCallBack;
            return this;
        }

        /**
         * 加载Drawable
         */
        public void loadDrawable() {
            ImageLoadUtils imageLoadUtils = new ImageLoadUtils(this);
            imageLoadUtils.loadDrawable();
        }

        /**
         * 加载Url
         */
        public void loadUrl() {
            ImageLoadUtils imageLoadUtils = new ImageLoadUtils(this);
            imageLoadUtils.loadUrl();
        }

        /**
         * 加载本地图片文件
         */
        public void loadLocalImageFile() {
            ImageLoadUtils imageLoadUtils = new ImageLoadUtils(this);
            imageLoadUtils.loadLocalImageFile();
        }

        /**
         * 加载Uri
         */
        public void loadUri() {
            ImageLoadUtils imageLoadUtils = new ImageLoadUtils(this);
            imageLoadUtils.loadUri();
        }

        interface ImageLoadCallBack {

            void onSuccess(Drawable bitmap);

            void onFail();
        }
    }

    /**
     * 加载Drawable资源
     */
    private void loadDrawable() {
        if (builder.imageView == null || builder.context == null) {
            if (builder.imageLoadCallBack != null) {
                builder.imageLoadCallBack.onFail();
            }
        } else {
            DrawableRequestBuilder<Integer> drawableRequestBuilder = Glide.with(builder.context).load(builder.drawable);
            if (builder.isNeedCache) {
                drawableRequestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL);
            }
            if (builder.isCircle) {
                drawableRequestBuilder.transform(new GlideCircleTransform(builder.context));
            } else if (builder.isRaadius) {
                drawableRequestBuilder.transform(new GlideRoundTransform(builder.context, builder.radius));
            }
            drawableRequestBuilder.into(new GlideDrawableImageViewTarget(builder.imageView) {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    super.onResourceReady(resource, animation);
                    if (builder.imageLoadCallBack != null) {
                        builder.imageLoadCallBack.onSuccess(resource);
                    }
                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    super.onLoadFailed(e, errorDrawable);
                    if (builder.imageLoadCallBack != null) {
                        builder.imageLoadCallBack.onFail();
                    }
                }
            });
        }
    }

    /**
     * 加载网络图片地址
     */
    private void loadUrl() {
        if (builder.imageView == null || builder.context == null || TextUtils.isEmpty(builder.url)) {
            if (builder.imageLoadCallBack != null) {
                builder.imageLoadCallBack.onFail();
            }
        } else {
            DrawableRequestBuilder<String> drawableRequestBuilder = Glide.with(builder.context).load(builder.url);
            if (builder.isNeedCache) {
                drawableRequestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL);
            }
            if (builder.isCircle) {
                drawableRequestBuilder.transform(new GlideCircleTransform(builder.context));
            } else if (builder.isRaadius) {
                drawableRequestBuilder.transform(new GlideRoundTransform(builder.context, builder.radius));
            }
            drawableRequestBuilder.into(new GlideDrawableImageViewTarget(builder.imageView) {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    super.onResourceReady(resource, animation);
                    if (builder.imageLoadCallBack != null) {
                        builder.imageLoadCallBack.onSuccess(resource);
                    }
                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    super.onLoadFailed(e, errorDrawable);
                    if (builder.imageLoadCallBack != null) {
                        builder.imageLoadCallBack.onFail();
                    }
                }
            });
        }
    }

    /**
     * 加载本地路径存储图片
     */
    private void loadLocalImageFile() {
        if (builder.imageView == null || builder.context == null) {
            if (builder.imageLoadCallBack != null) {
                builder.imageLoadCallBack.onFail();
            }
        } else {
            File file = new File(builder.photoFilePath);
            if (!file.exists()) {
                builder.imageLoadCallBack.onFail();
            } else {
                DrawableRequestBuilder<File> drawableRequestBuilder = Glide.with(builder.context).load(file);
                if (builder.isNeedCache) {
                    drawableRequestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL);
                }
                if (builder.isCircle) {
                    drawableRequestBuilder.transform(new GlideCircleTransform(builder.context));
                } else if (builder.isRaadius) {
                    drawableRequestBuilder.transform(new GlideRoundTransform(builder.context, builder.radius));
                }
                drawableRequestBuilder.into(new GlideDrawableImageViewTarget(builder.imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        if (builder.imageLoadCallBack != null) {
                            builder.imageLoadCallBack.onSuccess(resource);
                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        if (builder.imageLoadCallBack != null) {
                            builder.imageLoadCallBack.onFail();
                        }
                    }
                });
            }
        }
    }

    /**
     * 加载Uri
     */
    private void loadUri() {
        if (builder.imageView == null || builder.context == null || builder.uri == null) {
            if (builder.imageLoadCallBack != null) {
                builder.imageLoadCallBack.onFail();
            }
        } else {
            DrawableRequestBuilder<Uri> drawableRequestBuilder = Glide.with(builder.context).load(builder.uri);
            if (builder.isNeedCache) {
                drawableRequestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL);
            }
            if (builder.isCircle) {
                drawableRequestBuilder.transform(new GlideCircleTransform(builder.context));
            } else if (builder.isRaadius) {
                drawableRequestBuilder.transform(new GlideRoundTransform(builder.context, builder.radius));
            }
            drawableRequestBuilder.into(new GlideDrawableImageViewTarget(builder.imageView) {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    super.onResourceReady(resource, animation);
                    if (builder.imageLoadCallBack != null) {
                        builder.imageLoadCallBack.onSuccess(resource);
                    }
                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    super.onLoadFailed(e, errorDrawable);
                    if (builder.imageLoadCallBack != null) {
                        builder.imageLoadCallBack.onFail();
                    }
                }
            });
        }
    }

}
