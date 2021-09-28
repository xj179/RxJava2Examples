package com.nanchen.rxjava2examples.model;

import java.util.List;

/**
 * Category 返回model
 *
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * String: 2017-06-30  13:23
 */

public class CategoryResult2 {
    /**
     *     "page": 1,
     *     "page_count": 10,
     *     "status": 100,
     *     "total_counts": 96
     */
    public List<ResultsBean> data;
    public int page;
    public int page_count;
    public int status;
    public int total_counts;

    public static class ResultsBean {
        /**
         {
         "_id": "5e959250808d6d2fe6b56eda",
         "author": "鸢媛",
         "category": "Girl",
         "createdAt": "2020-05-25 08:00:00",
         "desc": "与其安慰自己平凡可贵，\n不如拼尽全力活得漂亮。 ​ ​​​​",
         "images": [
         "http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2"
         ],
         "likeCounts": 8,
         "publishedAt": "2020-05-25 08:00:00",
         "stars": 1,
         "title": "第96期",
         "type": "Girl",
         "url": "http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2",
         "views": 20762
         }
         */
        public String _id;
        public String author;
        public String category;
        public String createdAt;
        public String desc;
        public List<String> images;
        public int likeCounts;
        public String publishedAt;
        public int stars;
        public String title;
        public String type;
        public String url;
        public int views;
    }
}
