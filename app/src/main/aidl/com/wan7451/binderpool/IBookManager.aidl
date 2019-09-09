// IBookManager.aidl
package com.wan7451.binderpool;

import com.wan7451.binderpool.Book;
import com.wan7451.binderpool.IOnNewBookArrivedListener;

interface IBookManager {
       List<Book> getBookList();
       void addBook(in Book book);
       void registerListener(IOnNewBookArrivedListener l);
       void unregisterListener(IOnNewBookArrivedListener l);
}
