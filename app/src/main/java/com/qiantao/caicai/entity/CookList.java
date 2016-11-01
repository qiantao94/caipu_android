package com.qiantao.caicai.entity;

import android.databinding.BaseObservable;

import java.util.List;

/**
 * Created by qiantao on 2016/10/13.
 * 获取菜谱列表返回的数据实体，包含菜谱详情的list
 */

public class CookList extends BaseObservable {

    /**
     * status : true
     * total : 290
     * tngou : [{"count":14544,"description":"7）加盖，置于蒸锅内，隔水以文火炖2个小时，以表面呈现少量泡沫，有点沸腾、粘稠感和蛋清香味为蒸好的标准","fcount":0,"food":"干血燕燕窝,冰糖","id":183,"images":"","img":"/cook/150802/1340f07baad474a757825191701d5e1e.jpg","keywords":"燕窝 纯净水 膨胀 冰糖 清洗 ","name":"冰糖燕窝","rcount":0},{"count":9721,"description":"煮开焯去血沫，再洗净备用3，锅中放入猪脚，黄豆，姜片和料酒加入足够的清水，大火煮开4，关小火炖一个半到两个小时，出锅前加盐调味即可","fcount":0,"food":"猪脚,黄豆,料酒","id":228,"images":"","img":"/cook/150802/6d4d8bee058f471ff8c8d307d433223b.jpg","keywords":"黄豆 猪脚 清水 洗净 小时 ","name":"黄豆猪脚汤","rcount":0},{"count":6149,"description":"大米淘洗干净，用适量水浸泡30分钟后，加入锅里，再加入冰糖，一起继续煮15分钟","fcount":0,"food":"水发银耳,大米,果丹皮,冰糖","id":449,"images":"","img":"/cook/150802/0c8f93ae57fbf175361d6949932a285e.jpg","keywords":"果丹皮 加入 冰糖 银耳 大米 ","name":"消食养颜粥","rcount":0},{"count":3186,"description":"小诀窍功效：能温和、安全地促进肌肤新陈代谢、血液循环、更新老化角质层、分解表皮层的黑色素，让肌肤变得更加均匀、光滑、白皙","fcount":0,"food":"绿茶,葡萄,凤梨,蜂蜜,柠檬","id":669,"images":"","img":"/cook/150802/1ab358cb54759f7f83928c4d5b97be87.jpg","keywords":"绿茶 柠檬 新陈代谢 肌肤 蜂蜜 ","name":"加味绿茶","rcount":0},{"count":2001,"description":"材料菊花10克，绿豆沙30克，柠檬10克，蜂蜜少许做法1）将菊花放入水中煮沸2）将榨汁的柠檬和绿豆沙的汁注入菊花水中搅拌，放入少量蜂蜜即可饮用","fcount":0,"food":"菊花,绿豆沙,柠檬,蜂蜜","id":671,"images":"","img":"/cook/150802/ab3beb48fc7faf634c821e3f6f2ae08c.jpg","keywords":"菊花 绿豆沙 柠檬 蜂蜜 放入 ","name":"绿豆菊花茶","rcount":0},{"count":2014,"description":"菜谱简介提高细胞活力，加速脂肪消化，调节人体的生理机能，改善肌肤光泽，减慢皮肤老化，是美白养颜的良方","fcount":1,"food":"芦荟,菊花,红茶包,蜂蜜","id":672,"images":"","img":"/cook/150802/8c6fdf8e013697a9efe47494d7ae4b5f.jpg","keywords":"芦荟 蜂蜜 红茶 菊花 消化 ","name":"芦荟红茶","rcount":0},{"count":2700,"description":"最后保持水的量只到猪脚的一半水位，不同于普通的卤猪脚是全部用水覆盖住的．这样卤出来的猪脚Ｑ香弹牙，但不如软烂的猪脚入味，小白家是最爱吃这种有弹性的猪脚）当时我想说这么少水会不会煮的不入味，，真想给再加点水进去，小白坚持不让我加说老爸怎么说就怎么做．那就没有再加水，半锅量的水卤的猪脚，大火煮开后再转小火慢慢的卤，中间要把猪脚翻好几次身．最后卤至收汁，锅底只留一层水有些烧干的样子即可．","fcount":0,"food":"猪前腿,生姜,大蒜,香葱,酱油,羹,米酒,饭","id":701,"images":"","img":"/cook/150802/e060ac44528822a9bcdd625dec0f031c.jpg","keywords":"猪脚 放入 调羹 台式 米酒 ","name":"卤猪脚","rcount":0},{"count":1887,"description":"材料新鲜山药200克，白果10颗，红枣15颗，枸杞子20颗，干莲子10颗，清水6饭碗，调味料：喜欢甜味的放冰糖10-15克，喜欢咸味的放盐1/2小匙冰糖8克做法1","fcount":1,"food":"山药,白果,红枣,枸杞子,干莲子,饭,冰糖","id":723,"images":"","img":"/cook/150802/3f8f253e8ba85a9c4914837b83174852.jpg","keywords":"加入 清水 红枣 莲子 调味料 ","name":"养生美颜汤","rcount":0},{"count":2145,"description":"重新换上新鲜的水，放入乌鸡、花旗参、红枣、桂圆肉、姜片、枸杞，大火煮1小时，文火1小时，一道美味的花旗参乌鸡汤就做成啦","fcount":0,"food":"乌鸡,花旗参,红枣,桂圆肉,姜片,枸杞","id":791,"images":"","img":"/cook/150802/76eabf18da9f7aa14a4fe216f37e998d.jpg","keywords":"花旗参 乌鸡 桂圆肉 放入 煮沸 ","name":"花旗参乌鸡汤","rcount":0},{"count":1209,"description":"材料日本南瓜一个500克，银耳一朵，枸杞8颗，冰糖适量做法１，银耳事先泡发好２，日本南瓜一个洗净３，从头部五分之一处切开，用小勺将内部瓜籽去除４，南瓜变成一个容器５，将银耳和枸杞倒入，放入适量的水和冰糖６，南瓜盖好盖后，放在锅中，上锅蒸25-３０分钟即可","fcount":0,"food":"日本南瓜,银耳,枸杞,冰糖","id":972,"images":"","img":"/cook/150802/986e0071de7963c0bbda1d700073d64f.jpg","keywords":"南瓜 适量 枸杞 日本 冰糖 ","name":"银耳枸杞南瓜盅","rcount":0},{"count":842,"description":"菜谱简介姜汁红茶能不能减肥我不知道，不过我肯定柠檬和薄荷能减肥，因为2者都具有抑制食欲和调整消化肠胃的功效","fcount":0,"food":"冰糖,柠檬,薄荷叶","id":1460,"images":"","img":"/cook/150802/826f2a8f8c8656621e4d61e5af474ee7.jpg","keywords":"柠檬 适量 冰糖 一杯 薄荷叶 ","name":"冰糖柠檬薄荷饮","rcount":0},{"count":793,"description":"那时候对做饭还不是很精通，回家到了厨房就把油梨剥开皮，咬了好大一口","fcount":0,"food":"燕麦,豆浆,梨","id":2335,"images":"","img":"/cook/150802/9c0752ecce7e8439ec949f9afec469d6.jpg","keywords":"油梨 燕麦 喜欢 到了 那种 ","name":"油梨燕麦粥","rcount":0},{"count":853,"description":"材料糯米，绿豆，苡米，苕实做法1、将糯米、绿豆、苡米、苕实浸泡一晚；2、洗净后放入锅中煲熬至材料软，起码1-2个小时","fcount":0,"food":"糯米,绿豆,苡米,苕实","id":2620,"images":"","img":"/cook/150802/34767272c84b91ee05c26008fcaae35d.jpg","keywords":"材料 绿豆 糯米 欣赏 多谢 ","name":"苡米苕实绿豆糯米粥","rcount":0},{"count":464,"description":"小诀窍这道甜羹,桂圆、蜜枣、葡萄干都象征着团团圆圆、甜甜蜜蜜、吉祥如意；藕粉则是祝福阖家融融乐乐，亲密无间；本来应该撒上点红绿蜜饯丝预祝生活五彩缤纷的意思，我家里没有，也不喜欢吃，就没加，放了几粒枸杞装饰一下好了，也没必要只为了拍照好看，满世界去找红绿丝了","fcount":0,"food":"水晶,梨,藕粉,桂圆,蜜枣,葡萄干,冰糖,枸杞子","id":3131,"images":"","img":"/cook/150802/e8547e09c22773bc4d777d9e7d891416.jpg","keywords":"蜜枣 藕粉 桂圆 葡萄干 冰糖 ","name":"藕粉梨干果羹","rcount":0},{"count":527,"description":"并不跑步，只在如茵的草地上，独个儿慢慢走上两三公里，漫长的海岸线，没有尽头一般地在脚底下延伸，近处和远处，全是水雾朦胧的一片，看不清真切的事物","fcount":0,"food":"龙眼干,红茶包","id":3186,"images":"","img":"/cook/150802/a9dc077d66ce5b68837ee4d1f5bb3d51.jpg","keywords":"龙眼 海边 蜂蜜 红茶 湿度 ","name":"养颜龙眼蜂蜜茶","rcount":0},{"count":556,"description":"可用一根筷子插入到糕的最中间位置，然后再拔出来，用手摸摸筷子，如果没有粘手感觉，表示糕蒸熟了","fcount":0,"food":"桂花,红枣,马蹄粉","id":3347,"images":"","img":"/cook/150802/5f94712ea6bd13c19ba84582f80c652c.jpg","keywords":"清水 马蹄 红枣 加入 倒入 ","name":"桂花红枣糕","rcount":0},{"count":746,"description":"材料花椒，八角2个，冰糖，酱油，姜，葱2个，蒜蓉，陈醋，香菜碎，香油做法1","fcount":0,"food":"花椒,八角,冰糖,酱油,蒜蓉,陈醋,香菜,香油","id":3922,"images":"","img":"/cook/150802/d45744b7557735bd56904eee5c3aca12.jpg","keywords":"猪皮 还真是 酱油 花椒 价廉物美 ","name":"胶质美容","rcount":0},{"count":1110,"description":"以前总觉得番茄和菜花搭配不好吃，一搭配才知道味道超级好，吃了好多，结果减肥餐也没了踪影","fcount":0,"food":"番茄,菜花,酱油,十三香粉,鸡精,料酒","id":4330,"images":"","img":"/cook/150802/ceb8b555a5b58d62bd13fd32f83bc6e2.jpg","keywords":"菜花 搭配 香粉 好吃 鸡精 ","name":"番茄菜花","rcount":0},{"count":621,"description":"能与柚子产生相互作用的药物还很多，像他汀类药物，如洛伐他汀、血脂康、舒降之、立普妥；钙拮抗剂类，如硝本地平、尼莫地平、尼索地平、费乐地平；安定类，如舒乐安定、佳乐定；抗组胺药，如特非那丁；免疫抑制剂，如环孢素；咖啡因、避孕药等中枢兴奋剂等，柚子都以使以上药物作用加强或者失效，因此，服用以上这些","fcount":0,"food":"蜜柚,蜂蜜","id":5683,"images":"","img":"/cook/150802/3fab6e4a930532bb7f82f0752403dfe0.jpg","keywords":"柚子 地平 蜂蜜 苦味 发酵 ","name":"蜂蜜柚子茶","rcount":0},{"count":1091,"description":"菜谱简介这段时间应我家先生同志的号召，号召俺每天搅拌些不同的果汁来喝喝，不过这点子真不错哦，秋燥的确会造成皮肤干渴、缺水，最近坚持天天喝不同的果汁后，皮肤真的是水噹噹&nb","fcount":0,"food":"桃,玉米,番茄","id":5685,"images":"","img":"/cook/150802/56d31ba0581b58ccf6d480d2c63317a3.jpg","keywords":"玉米 弥猴桃 搅拌机 就可以 喜欢 ","name":"皮肤水噹噹果汁来帮忙","rcount":0}]
     */
    private boolean status;

    private int total;

    /**
     * count : 14544
     * description : 7）加盖，置于蒸锅内，隔水以文火炖2个小时，以表面呈现少量泡沫，有点沸腾、粘稠感和蛋清香味为蒸好的标准
     * fcount : 0
     * food : 干血燕燕窝,冰糖
     * id : 183
     * images :
     * img : /cook/150802/1340f07baad474a757825191701d5e1e.jpg
     * keywords : 燕窝 纯净水 膨胀 冰糖 清洗
     * name : 冰糖燕窝
     * rcount : 0
     */
    private List<CookDetail> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
        notifyChange();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        notifyChange();

    }

    public List<CookDetail> getTngou() {
        return tngou;
    }

    public void setTngou(List<CookDetail> tngou) {
        this.tngou = tngou;
        notifyChange();
    }
}
