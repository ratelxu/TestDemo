package com.nanchen.rxjava2examples.module.rxjava2.operators;

import java.util.ArrayList;
import java.util.List;

import com.nanchen.rxjava2examples.R;
import com.nanchen.rxjava2examples.module.view.WLLayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 操作符Fragment
 * <p>
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-06-20  14:55
 */

public class WebViewListViewFragment extends Fragment {

    private List<String> list = new ArrayList<>();
    private WLLayout wl;

    String htmlString = "<h1>Title1</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p><br>" +
            "<img src=\"http://img.blog.csdn.net/20160405153830915\" width=\"330px\" height=\"330px\"/><br>" +
            "<h1>Title2</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p><br>" +
            "<img src=\"http://img.blog.csdn.net/20160405153830915\" width=\"330px\" height=\"330px\"/><br>" +
            "<h1>Title3</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<img src=\"http://img.blog.csdn.net/20160405153830915\" width=\"330px\" height=\"330px\"/><br>" +
            "<h1>Title4</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<img src=\"http://img.blog.csdn.net/20160405153830915\" width=\"330px\" height=\"330px\"/><br>" +
            "<h1>Title5</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<img src=\"http://img.blog.csdn.net/20160405153830915\" width=\"330px\" height=\"330px\"/><br>" +
            "<h1>Title6</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<img src=\"http://img.blog.csdn.net/20160405153830915\" width=\"330px\" height=\"330px\"/><br>";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        wl = (WLLayout) view.findViewById(R.id.wl);

        // WebView
        WebView myWebView = (WebView) view.findViewById(R.id.webview);
        // myWebView.loadData(htmlString, "text/html", "utf-8");
        htmlString = "http://fanshuapp.com/api/webview?page=post&id=0733957810ed32b627903ed3109f1c5d87cee777e&v=3.9.3";
        myWebView.loadUrl(htmlString);
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        };
        myWebView.setWebViewClient(webViewClient);

        // ListView
        for (int i = 0; i < 60; i++) {
            list.add("评论：" + i);
        }
        ListView lv = (ListView) view.findViewById(R.id.list_view);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.header_view, lv, false);
        lv.addHeaderView(header);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
