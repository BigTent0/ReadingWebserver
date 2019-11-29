package com.example.demo15.Service;

import com.example.demo15.Common.ServerResponse;
import com.example.demo15.Model.BookBean;

import java.util.List;

public interface ISearchService {
        //List<BookBean> search(String name);
        ServerResponse<List<BookBean>> search(String fileName);
}
