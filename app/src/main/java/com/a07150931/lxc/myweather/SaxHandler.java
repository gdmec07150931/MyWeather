package com.a07150931.lxc.myweather;



import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus1 on 2016/12/15.
 */
public class SaxHandler extends DefaultHandler {

    // 保存省份和城市，key 是省份名称，value 是省份下的所有城市名的列表；
    private Map<String,List<String>> cityMap = new HashMap<String,List<String>>();

    // 临时保存读取到的省份和城市名称；
    String cityName = "";
    String provinceName = "";
    public Map<String,List<String>> getCityMap(){
        return cityMap;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("Province".equals(qName)){   // 读取省份信息；
            provinceName = attributes.getValue("name");
            // 每读到一个省份节点，就将省份名字存入 map ;
            cityMap.put(provinceName,new ArrayList<String>());
        }else{
            cityName = attributes.getValue("name");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("City".equals(qName)){
            // 读取到城市节点的结束标签后，将读到的城市存在所在省份对应的 list 里面；
            cityMap.get(provinceName).add(cityName);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
