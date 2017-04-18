/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bccnw.reader.meimeilovereader.api.weixin;

/**
 * 该类定义了微博授权时所需要的参数。
 * 
 * @author SINA
 * @since 2013-09-29
 */
public interface WXConstants {

    // APP_ID 替换为你的应用从官方网站申请到的合法appId
    public static final String APP_ID = "wx5c9f8c91af605d16";
    public static final String APP_SECRET = "bfb0a78a96abe5bcc86490d754e8b435";
    public static final String STitle = "showmsg_title";
    public static final String SMessage = "showmsg_message";
    public static final String BAThumbData = "showmsg_thumb_data";
}
