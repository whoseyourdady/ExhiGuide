package com.scut.exguide.assist;



import com.scut.exguide.ui.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class RoundListViewAdapter_attribute extends BaseAdapter implements
		ListAdapter {

	final String[] strs1 = { "厂商", "级别", "发动机", "长×宽×高(mm)", "车身结构" 
			, "最高车速(km/h)", "官方0-100加速(s)", "实测0-100加速(s)","实测100-0制动(m)","实测油耗(L)"};
	final String[] strs2= { "一汽-大众", "紧凑型车", "1.6L 105马力 L4", "5挡手动", "4门5座三厢车" 
			, "185", "11.8", "","",""};
	
	
	final String[] strs3={"游戏名称", "开发厂商", "游戏类型", "发行公司", "语言版本", "发售时间", "运行平台"  };
	final String[] strs4={"古墓丽影9", "Crystal Dynamics", "单机游戏", "", "英文" , "", "PC PS3 X360" };
	
	final String[] strs5={"拍摄场地", "拍摄数量", "服装提供", "造型提供", "化妆提供", "服务承诺" };
	final String[] strs6={"时尚街景+空中花园+内景（任选）", "Crystal Dynamics", "提供服装3套（可自带，包含在3套内）", "专业造型3组", "免费化妆造型，提供专业台湾仿真美目睫毛一对，提供拍摄所需要的所有精美道具饰品，如粉扑、假发，发饰等" , "深圳蔓诺视觉郑重承诺此套系无任何隐形消费" };
	
	
	final String[] strs7={"参展商", "展会描述" };
	final String[] strs8={"一汽大众/广州本田/GMC/保时捷/法拉利", "依托中国巨大的汽车消费市场和快速发展的中国汽车工业，北京国际汽车展览会在展览规模、国际化水准、展品质量以及在全球的影响力逐届提高，受到中外汽车界、新闻界和社会各界的高度关注和积极参与。众多国际知名汽车公司将北京国际汽车展览会列为全球最重要的国际级车展，中国本土汽车企业也将北京国际汽车展览会作为展示自主知识品牌、推出最新科技成果的首选平台。"};
	
	final String[] strs9={"参展商", "日期","门票", "展会描述" };
	final String[] strs10={" KONAMI、卡普空、SQUARE ENIX", "2012年9月20日-9月23日","1000日元","20和21日两天为媒体/商务日，22和23日两天为公众日"};
	
	final String[] strs11={"日期", "展会描述" };
	final String[] strs12={"2012年12月8-9日", "中国婚博会--民政部门举办的国家级婚博会、全球超大规模、世界级品牌结婚展。每年在北京、上海、广州等地同时举办春夏秋冬四季展，先后有30多个国家的名品名店、名设计师、名流明星来中国婚博会发布每季国际前沿结婚时尚，中国婚博会已成为中国百万新人首选的结婚采购品质平台和中国结婚时尚风向标。2012冬季中国婚博会将于12月8日-9日再次在国家会议中心隆重召开！届时，来自英、美、法、意、日、韩……的3000个国际国内顶级品牌、50万款结婚新品，送现金、送家电……以3000万盛大特惠北京结婚新人，让您享受高性价比一站式结婚采购服务、轻松筹备您的品质婚礼！"};
	
	private Context context;
	private LayoutInflater inflater;

	public RoundListViewAdapter_attribute(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return strs11.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(R.layout.item_attribute, null);
		final View ll = convertView;
		TextView tv = (TextView) convertView
				.findViewById(R.id.navigation_more_checklist01_textview);
		tv.setText(strs11[position]);

		TextView tv2 = (TextView) convertView
				.findViewById(R.id.navigation_more_checklist02_textview);
		tv2.setText(strs12[position]);
		
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();

				// 颜色
				switch (position) {
				case 0:
					ll.setBackgroundResource(R.drawable.app_list_corner_round_top);
					break;

				default:
					if (position == strs1.length - 1) {
						ll.setBackgroundResource(R.drawable.app_list_corner_round_bottom);
					} else {
						ll.setBackgroundResource(R.drawable.app_list_corner_shape);
					}
				}

				try {
					context.startActivity(intent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		return convertView;
	}

}
