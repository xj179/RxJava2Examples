package com.nanchen.rxjava2examples.model;

import java.util.List;

/**
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-07-03  9:17
 */

public class ArticleList {
    /**
     "counts": 10,
     "data": [
     {
     "_id": "5e7226a3907078dcbfe95cd8",
     "author": "Jason",
     "category": "GanHuo",
     "createdAt": "2015-08-17 02:32:37",
     "desc": "Material Design样式的加载效果",
     "images": [],
     "likeCounts": 0,
     "publishedAt": "2015-08-17 03:54:47",
     "stars": 1,
     "title": "ProgressCircle",
     "type": "Android",
     "url": "https://github.com/alokvnair/ProgressCircle",
     "views": 139
     },没有酸味，最好是让对方提供一份检验报告之类的东西，还有就是油的色泽，是否澄清透明","disease":"胆固醇脓胸,家族性高胆固醇血症,猝死型冠心病,胆囊胆固醇沉着症,磷脂酰胆碱-胆固醇酰基转移酶缺乏,胆固醇肺炎,小儿细胞外胆固醇沉着综合征,蓝鼓膜与胆固醇肉芽肿,冠心病","fcount":0,"food":"板绞油,油抒,羊油,玉米油,玛琪琳,裹入油,油皮,油面,油豆角,油豆腐,鸭油,鹅油卷,青甘鱼,酱油露,白脱油,芝麻油,牛油汁,炼制猪油,胡麻油,棕榈油,猪网油,猪背油","id":1599,"img":"/food/150804/2ad85610af25a980b5fd6d1828bbade1.jpg","keywords":"棕榈油 体质 食用 就是 饱和 ","name":"棕榈油","rcount":0,"summary":"<p>【性质】平<\/p> \n<p>【五味】辛<\/p> \n<p>【热量】900.00大卡(千焦)/100克 <\/p> \n<p>【功效】抑癌抗瘤<\/p> \n<p>【棕榈油是什么】 棕榈油是从油棕树上的棕果(Elaeis Guineensis)中榨取出来的，果肉压榨出的油称为棕榈油( Palm Oil)，而果仁压榨出的油称为棕榈仁油(Palm Kernel Oil)，两种油的成分大不相同。棕榈油主要含有棕榈酸(C 16)和油酸(C 18)两种最普通的脂肪酸，棕榈油的饱和程度约为50%;棕榈仁油主要含有月桂酸(C 12)，饱和程度达80...<\/p>","symptom":"煤尘或胆固醇结晶的黏痰,近迫性心肌梗塞,痉挛性偏瘫步态,脑血管痉挛,血浆胆固醇水平高,心脑血管意外,脑血管动静脉畸形,对侧肢体偏瘫,偏瘫"}]
     */

    public int counts;
    public List<ArticleCategory> data;
    public int status;

    @Override
    public String toString() {
        return "ArticleList{" +
                "counts=" + counts +
                ", data=" + data +
                ", status=" + status +
                '}';
    }

    public static class ArticleCategory{
        /**
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
         "status": 100黏痰,近迫性心肌梗塞,痉挛性偏瘫步态,脑血管痉挛,血浆胆固醇水平高,心脑血管意外,脑血管动静脉畸形,对侧肢体偏瘫,偏瘫
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

        @Override
        public String toString() {
            return "ArticleDetail{" +
                    "_id='" + _id + '\'' +
                    ", author='" + author + '\'' +
                    ", category='" + category + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", images=" + images +
                    ", likeCounts=" + likeCounts +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", stars=" + stars +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", views=" + views +
                    '}';
        }
    }
}
