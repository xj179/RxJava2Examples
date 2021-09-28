package com.nanchen.rxjava2examples.model;

import java.util.List;

/**
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * String: 2017-07-03  9:18
 */

public class ArticleDetail {
    /**
     {
     "data": {
     "_id": "5e7225cb907078dcbfe9563b",
     "author": "lijinshanmx",
     "category": "GanHuo",
     "content": "",
     "createdAt": "2018-05-05 00:00:00",
     "desc": "创建并显示进度，数据或错误视图，简单的方法！",
     "images": [],
     "index": 0,
     "isOriginal": true,
     "license": null,
     "likeCounts": 0,
     "likes": [],
     "originalAuthor": null,
     "publishedAt": "2018-05-05 00:00:00",
     "stars": 1,
     "status": 1,
     "tags": [],
     "title": "StateViews",
     "type": "Android",
     "updatedAt": "2018-05-05 00:00:00",
     "url": "https://github.com/medyo/StateViews",
     "views": 102
     },
     "status": 100
     }
     */
    public Article data;
    public int status;

    public  static  class  Article{
        public String _id;
        public String author;
        public String category;
        public String content;
        public String createdAt;
        public String desc;
        public List<String> images;
        public int index;
        public boolean isOriginal;
        public String license;
        public int likeCounts;
        public List<String> likes;
        public String originalAuthor;
        public String publishedAt;
        public int stars;
        public int status;
        public List<String> tags;
        public String title;
        public String type;
        public String updatedAt;
        public String url;
        public int views;

        @Override
        public String toString() {
            return "Article{" +
                    "_id='" + _id + '\'' +
                    ", author='" + author + '\'' +
                    ", category='" + category + '\'' +
                    ", content='" + content + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", images=" + images +
                    ", index=" + index +
                    ", isOriginal=" + isOriginal +
                    ", license='" + license + '\'' +
                    ", likeCounts=" + likeCounts +
                    ", likes=" + likes +
                    ", originalAuthor='" + originalAuthor + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", stars=" + stars +
                    ", status=" + status +
                    ", tags=" + tags +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", url='" + url + '\'' +
                    ", views=" + views +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ArticleDetail{" +
                "data=" + data +
                ", status=" + status +
                '}';
    }
}
