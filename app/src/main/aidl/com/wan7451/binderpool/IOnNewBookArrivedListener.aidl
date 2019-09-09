// IOnNewBookArrivedListener.aidl
package com.wan7451.binderpool;

import com.wan7451.binderpool.Book;

interface IOnNewBookArrivedListener {
   void onNewBookArrived(in Book book);
}
