package com.dujun.lib.bean;

import android.content.Context;

import androidx.annotation.Keep;

import com.dujun.common.basebean.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Routee
 * @date 2019/1/25
 * @mail wangchao@innotechx.com
 */
@Keep
public class ConfigInfo extends BaseResponse {
    public List<Country> countries = new ArrayList<>();
    public LoginConfig login = new LoginConfig();
    public H5 h5 = new H5();
    public String ignoreWifiStateUser;
    @SerializedName("common")
    public CommonConfig commonConfig = new CommonConfig();
    @SerializedName("video_setting")
    public VideoConfig videoConfig = new VideoConfig();

    @SerializedName("channel_list")
    public List<ChannelItem> channelList;

    @Keep
    public class ChannelItem {
        public int id;
        public String name;
    }

    @Keep
    public class VideoConfig {
        public int width = 640;
        public int height = 360;
        @SerializedName("frame_rate_fps")
        public int frameRateFps = 15;
        public int bitrate = 1000;
        @SerializedName("orientation_mode")
        public int orientationMode = 2;
        @SerializedName("channel_profile")
        public int channelProfile = 1;
        @SerializedName("client_role")
        public int clientRole = 1;
        @SerializedName("audio_profile")
        public int audioProfile = 1;
        @SerializedName("audio_scenario")
        public int audioScenario = 1;
    }

    public MediaConfig getServerConfig(Context context) {
        try {
            String config = context.getSharedPreferences("server_config", Context.MODE_PRIVATE)
                    .getString("media_config", "");
            if (config == null || config.length() == 0) {
                return new MediaConfig();
            }
            MediaConfig serverConfig = new Gson().fromJson(config, MediaConfig.class);
            return serverConfig == null ? new MediaConfig() : serverConfig;
        } catch (Exception e) {
            e.printStackTrace();
            return new MediaConfig();
        }
    }

    public RecordConfig getRecordConfig(Context context) {
        try {
            String config = context.getSharedPreferences("server_config", Context.MODE_PRIVATE)
                    .getString("record_config", "");
            if (config == null || config.length() == 0) {
                return new RecordConfig();
            }
            RecordConfig serverConfig = new Gson().fromJson(config, RecordConfig.class);
            return serverConfig == null ? new RecordConfig() : serverConfig;
        } catch (Exception e) {
            e.printStackTrace();
            return new RecordConfig();
        }
    }

    @Keep
    public class RecordConfig extends BaseResponse {
        public int bitrate = 3000000;
        public int width = 540;
        public int height = 960;
        public int encodeType = 0;
    }

    @Keep
    public class MediaConfig extends BaseResponse {
        public Beauty beauty = new Beauty();
        public Encoder encoder = new Encoder();
        public Preview preview = new Preview();
        public Shape shape = new Shape();
        public Filter filter = new Filter();
    }

    @Keep
    public class Beauty {
        public Faceunity faceunity = new Faceunity();
    }

    @Keep
    public class Encoder {
        public String acodec;
        public int aprofile = 3;
        public int ascene = 1;
        public int achannel = 1;
        public int role = 1;
        public int bitrate = 1000;
        public int fps = 15;
        public int width = 640;
        public int height = 360;
        public String vcodec;
    }

    @Keep
    public class Filter {
        public Faceunity faceunity = new Faceunity();
    }

    @Keep
    public class Decoder {

    }

    @Keep
    public class Preview {
        public int fps = 15;
        public int width = 1280;
        public int height = 720;
    }

    @Keep
    public class Shape {
        public Faceunity faceunity = new Faceunity();
    }

    @Keep
    public class Faceunity {
        public float bright = 0;
        public float red = 0;
        public float skin = 0.45f;
        public float tooth = 0;
        public float white = 0.2f;

        public float eye = 0.1f;
        public float face = 0.1f;
        public float jaw = 0.5f;
        public float nose = 0;
        public float level = 1.0f;
        public String name = "";
    }

    @Keep
    public class CommonConfig {
        /**
         * max_call_history 通话历史最大显示数量
         */
        @SerializedName("max_call_history")
        public int maxCallHistory = 6;
        @SerializedName("default_mopi")
        public int defaultMopi = 30;
        @SerializedName("default_shoulian")
        public int defaultShoulian = 15;
        @SerializedName("default_dayan")
        public int defaultDayan = 10;
        @SerializedName("default_nickname")
        public String defaultNickname = "飞友";
        @SerializedName("video_max_second")
        public int videoMaxSecond;
        @SerializedName("video_min_second")
        public int videoMinSecond;
        @SerializedName("audio_max_second")
        public int audioMaxSecond;
        @SerializedName("audio_min_second")
        public int audioMinSecond;
        @SerializedName("video_max_size")
        public int videoMaxSize;
        @SerializedName("mess_share_title")
        public String messShareTitle;
        @SerializedName("mess_share_desc")
        public String messShareDesc;
        @SerializedName("mess_share_link")
        public String messShareLink;
        @SerializedName("img_max_width")
        public int imageMaxWidth;
        @SerializedName("img_max_height")
        public int imageMaxHeight;
        @SerializedName("album_image_show_second")
        public int albumImageShowSecond;
        @SerializedName("push_template_img")
        public String pushTemplateImg;
        @SerializedName("push_template_audio")
        public String pushTemplateAudio;
        @SerializedName("push_template_video")
        public String pushTemplateVideo;
        public int max;
        public int percent;
        @SerializedName("group_max_select")
        public int groupMaxSelect;
        @SerializedName("voice_max_person")
        public int voiceMaxPerson;
        @SerializedName("voice_share_title")
        public String voiceShareTitle;
        @SerializedName("voice_share_desc")
        public String voiceShareDesc;
        @SerializedName("voice_share_link")
        public String voiceShareLink;
        @SerializedName("push_template_text")
        public String pushTemplateText;
        @SerializedName("group_push_template_text")
        public String groupPushTemplateText;
        @SerializedName("group_push_template_img")
        public String groupPushTemplateImg;
        @SerializedName("group_push_template_audio")
        public String groupPushTemplateAudio;
        @SerializedName("group_push_template_video")
        public String groupPushTemplateVideo;
        @SerializedName("message_check_domains")
        public List<String> messageCheckDomains = new ArrayList<>();
        @SerializedName("voice_share_logo")
        public String voiceShareLogo;
    }

    @Keep
    public class Country {
        public String name = "";
        public String value = "";
        public String regex = "";
    }

    @Keep
    public class LoginConfig {
        /**
         * 是否显示用户信息编辑页
         */
        @SerializedName("is_show")
        public boolean isShow;
        /**
         * 是否允许跳过用户信息编辑页
         */
        @SerializedName("is_skip")
        public boolean isSkip;

        /**
         * 用于记录点击了跳过的用户
         */
        public String skipedUser = "";
    }

    @Keep
    public class H5 {
        public int version;
        public String faq = "";
        public String policy = "";
        public String protocol = "";
        public String complain = "";
    }
}