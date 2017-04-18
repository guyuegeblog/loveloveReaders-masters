package com.bccnw.reader.meimeilovereader.tool;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class NickTool {
	public  static String getName() {
        String[] name1 = {"赵", "钱", "孙", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李",
                "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李",
                "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李", "李",
                "李", "李", "周", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴",
                "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "吴", "郑", "王", "冯", "陈",
                "诸", "卫", "蒋", "沈", "韩", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨",
                "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨",
                "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨",
                "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨", "杨",
                "杨", "杨", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王",
                "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王", "王",
                "王", "王", "王", "王", "王", "王", "王", "王", "张", "张", "张", "张", "张", "张", "张", "张",
                "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于", "于",
                "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余", "余",
                "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张", "张",
                "张", "张", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈", "陈",
                "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜",
                "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
                "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐",
                "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常",
                "乐", "于", "时", "傅", "皮", "卡", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄",
                "和", "穆", "萧", "尹", "姚", "邵", "堪", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧",
                "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "粱",
                "杜", "阮", "蓝", "闵", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭",
                "梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍",
                "虞", "万", "支", "柯", "咎", "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗",
                "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉", "钮", "龚",
                "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊", "於", "惠", "甄", "魏", "家", "封",
                "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓",
                "牧", "隗", "山", "谷", "车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫",
                "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖", "武", "符", "刘", "景", "詹", "束", "龙",
                "司马", "上官", "欧阳", "夏侯", "诸葛", "东方", "尉迟", "公孙", "长孙", "慕容", "司徒", "西门"
        };

        String[] name2 = {"超", "媛", "念", "立", "思", "嘉", "雨", "伟", "权", "秋", "佩", "雅", "联", "如", "渠", "保", "室", "铜", "梧", "胤", "昱", "佳", "伊", "校", "诗", "节", "如",
                "阁", "耕", "宫", "古", "谷", "观", "桂", "贵", "国", "广", "冠", "汉", "翰", "航", "杭", "海", "豪", "浩", "皓", "和", "河", "贺", "恒", "弘", "虹", "宏", "红",
                "厚", "鹄", "虎", "华", "欢", "怀", "辉", "惠", "会", "奇", "吉", "骥", "嘉", "佳", "甲", "稼", "江", "坚", "剑", "锦", "经", "镜", "界", "竞", "介", "京", "建",
                "净", "精", "敬", "静", "靖", "津", "进", "菁", "景", "炯", "驹", "举", "炬", "君", "俊", "军", "骏", "郡", "峻", "恺", "楷", "康", "可", "克", "珂", "逵", "魁",
                "阔", "昆", "奎", "宽", "况", "乐", "雷", "岭", "廉", "霖", "麟", "灵", "利", "良", "联", "烈", "罗", "陵", "梁", "立", "礼", "力", "莉", "烁", "隆", "龙", "禄",
                "璐", "露", "律", "茂", "梦", "密", "铭", "明", "绵", "妙", "默", "木", "能", "年", "宁", "念", "怒", "庞", "佩", "培", "朋", "鹏", "屏", "平", "魄", "珀", "璞",
                "奇", "琦", "齐", "启", "谦", "乾", "茜", "倩", "芹", "琴", "青", "卿", "秋", "权", "求", "情", "渠", "全", "荃", "群", "泉", "然", "让", "仁", "任", "荣", "儒",
                "锐", "若", "瑞", "三", "瑟", "森", "韶", "绍", "尚", "商", "珊", "善", "生", "升", "声", "晟", "胜", "盛", "诗", "时", "石", "实", "凇", "慎", "设", "守", "随",
                "世", "寿", "仕", "余", "帅", "双", "朔", "硕", "水", "誓", "适", "书", "舒", "殊", "顺", "思", "嗣", "似", "松", "颂", "素", "岁", "棠", "泰", "腾", "添", "铁",
                "同", "桐", "童", "彤", "团", "涂", "图", "土", "万", "旺", "望", "王", "闻", "威", "薇", "嵬", "伟", "卫", "蔚", "文", "微", "巍", "玮", "为", "畏", "吾", "午",
                "西", "熙", "玺", "仙", "先", "孝", "湘", "祥", "行", "献", "享", "效", "兴", "夏", "宣", "协", "向", "校", "轩", "瑕", "衔", "筱", "羡", "相", "香", "贤", "翔",
                "杏", "新", "信", "幸", "心", "星", "绣", "秀", "欣", "鑫", "兴", "行", "雄", "许", "炫", "雪", "学", "旭", "璇", "勋", "萱", "迅", "亚", "雅", "扬", "耀", "彦",
                "延", "岩", "炎", "永", "砚", "演", "焱", "洋", "阳", "曜", "耀", "夜", "译", "逸", "伊", "义", "艺", "意", "异", "怡", "翼", "毅", "银", "瑛", "仪", "易", "寅",
                "印", "苡", "野", "业", "英", "璎", "盈", "颖", "影", "雍", "勇", "悠", "由", "游", "佑", "友", "瑜", "遇", "玉", "岳", "元", "宇", "愚", "钰", "裕", "郁", "于",
        };

        int len1 = name1.length - 1;
        int len2 = name2.length - 1;
        int random1 = (int) (Math.random() * len1);
        int random2 = (int) (Math.random() * len2);
        int random2_1 = (int) (Math.random() * len2);

        String name = "";
        if (random1 < 256) {
            int randomN = (int) (Math.random() * 2);
            if (randomN == 1) {
                name = name1[random1] + name2[random2];
            } else {
                name = name1[random1] + name2[random2] + name2[random2_1];
            }
        } else {
            name = name1[random1] + name2[random2] + name2[random2_1];
        }
        return name;
    }


    public String getChinese() {
        String str = null;
        int highPos, lowPos;
        Random random = new Random();
        highPos = (176 + Math.abs(random.nextInt(71)));//区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
        random = new Random();
        lowPos = 161 + Math.abs(random.nextInt(94));//位码，0xA0打头，范围第1~94列

        byte[] bArr = new byte[2];
        bArr[0] = (new Integer(highPos)).byteValue();
        bArr[1] = (new Integer(lowPos)).byteValue();
        try {
            str = new String(bArr, "GB2312");    //区位码组合成汉字
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }


    /***
     * 随机生成昵称
     */
    public String getRandomNick() {
        Random random = new Random(System.currentTimeMillis());
        /* 598 百家姓 */
        String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
                "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
                "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
                "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
                "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
                "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟",
                "徐", "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应",
                "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀",
                "羊", "于", "惠", "甄", "曲", "家", "封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山",
                "谷", "车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖", "武", "符", "刘", "景",
                "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "溥", "印", "宿", "白", "怀", "蒲", "邰", "从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠",
                "蒙", "池", "乔", "阴", "郁", "胥", "能", "苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳", "逄", "姬", "申", "扶", "堵", "冉", "宰", "郦", "雍", "却",
                "璩", "桑", "桂", "濮", "牛", "寿", "通", "边", "扈", "燕", "冀", "浦", "尚", "农", "温", "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹", "习",
                "宦", "艾", "鱼", "容", "向", "古", "易", "慎", "戈", "廖", "庾", "终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡", "国", "文", "寇", "广", "禄",
                "阙", "东", "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂", "晁", "勾", "敖", "融", "冷", "訾", "辛", "阚", "那", "简", "饶", "空",
                "曾", "毋", "沙", "乜", "养", "鞠", "须", "丰", "巢", "关", "蒯", "相", "查", "后", "荆", "红", "游", "郏", "竺", "权", "逯", "盖", "益", "桓", "公", "仉",
                "督", "岳", "帅", "缑", "亢", "况", "郈", "有", "琴", "归", "海", "晋", "楚", "闫", "法", "汝", "鄢", "涂", "钦", "商", "牟", "佘", "佴", "伯", "赏", "墨",
                "哈", "谯", "篁", "年", "爱", "阳", "佟", "言", "福", "南", "火", "铁", "迟", "漆", "官", "冼", "真", "展", "繁", "檀", "祭", "密", "敬", "揭", "舜", "楼",
                "疏", "冒", "浑", "挚", "胶", "随", "高", "皋", "原", "种", "练", "弥", "仓", "眭", "蹇", "覃", "阿", "门", "恽", "来", "綦", "召", "仪", "风", "介", "巨",
                "木", "京", "狐", "郇", "虎", "枚", "抗", "达", "杞", "苌", "折", "麦", "庆", "过", "竹", "端", "鲜", "皇", "亓", "老", "是", "秘", "畅", "邝", "还", "宾",
                "闾", "辜", "纵", "侴", "万俟", "司马", "上官", "欧阳", "夏侯", "诸葛", "闻人", "东方", "赫连", "皇甫", "羊舌", "尉迟", "公羊", "澹台", "公冶", "宗正",
                "濮阳", "淳于", "单于", "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐", "钟离", "宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空", "兀官", "司寇",
                "南门", "呼延", "子车", "颛孙", "端木", "巫马", "公西", "漆雕", "车正", "壤驷", "公良", "拓跋", "夹谷", "宰父", "谷梁", "段干", "百里", "东郭", "微生",
                "梁丘", "左丘", "东门", "西门", "南宫", "第五", "公仪", "公乘", "太史", "仲长", "叔孙", "屈突", "尔朱", "东乡", "相里", "胡母", "司城", "张廖", "雍门",
                "毋丘", "贺兰", "綦毋", "屋庐", "独孤", "南郭", "北宫", "王孙"};

        int index = random.nextInt(Surname.length - 1);
        String name = Surname[index]; //获得一个随机的姓氏

		/* 从常用字中选取一个或两个字作为名 */
        if (random.nextBoolean()) {
            name += getChinese() + getChinese();
        } else {
            name += getChinese();
        }
        try {
            name = new String(name.getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
    }
}
