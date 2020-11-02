package com.power.home.common.util;

import com.power.home.data.bean.CoursePlayerBean;
import com.power.home.data.bean.Music;

import java.util.ArrayList;

/**
 * Created by ZHP on 2020/5/15 0015.
 */
public class MusicList {

    public static ArrayList<Music> getMusicData() {
        ArrayList<Music> musicList = new ArrayList<Music>();
        CoursePlayerBean coursePlayerBean = (CoursePlayerBean) ACache.get(UIUtil.getContext()).getAsObject("MEDIA");
        if (null != coursePlayerBean) {
            for (int i = 0; i < coursePlayerBean.getCourses().size(); i++) {
                CoursePlayerBean.CoursesBean coursesBean = coursePlayerBean.getCourses().get(i);
                Music music = new Music();
                music.setUrl(coursesBean.getAudioUrl());
                music.setImage(coursesBean.getDisplayPic());
                music.setId(coursesBean.getId());
                music.setTitle(coursesBean.getTitle());
                music.setPosition(i);
                music.setFree(coursesBean.isFree());
                music.setCanPlay(coursesBean.isCanPlay());
                musicList.add(music);
            }

        }
        return musicList;
    }

    public static void saveCurrentMusicData(CoursePlayerBean coursePlayerBean) {
        ACache.get(UIUtil.getContext()).put("MEDIA", coursePlayerBean);
    }

    public static Music getMusic(int position) {
        ArrayList<Music> musicData = getMusicData();
        return musicData.get(position);
    }

    public static Music getNextMusic(int position) {
        ArrayList<Music> musicData = getMusicData();
        if (musicData.size() > 0) {
            if (position == musicData.size() - 1) {
                return null;
            } else {
                Music music = musicData.get(position + 1);
                if (music.isCanPlay() && StringUtil.isNotNullString(music.getUrl())) {
                    return music;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public static Music getNextVideo(int position) {
        ArrayList<Music> musicData = getMusicData();
        if (musicData.size() > 0) {
            if (position == musicData.size() - 1) {
                Music music = musicData.get(position);
                music.setAudio(false);
                music.setCurrentPosition(0);
                return music;
            } else {
                Music music = musicData.get(position + 1);
                music.setAudio(false);
                music.setCurrentPosition(0);
                return music;
            }
        }
        return null;
    }
}
