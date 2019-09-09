package com.wan7451.binderpool;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManagerImpl extends IBookManager.Stub {

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListeners = new RemoteCallbackList<>();


    @Override
    public List<Book> getBookList() throws RemoteException {
        Log.i("=====",Thread.currentThread().getName());
        return mBookList;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        Log.i("=====",Thread.currentThread().getName());
        mBookList.add(book);
        final int N = mListeners.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewBookArrivedListener l = mListeners.getBroadcastItem(i);
            if (l != null) {
                try {
                    l.onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListeners.finishBroadcast();
    }

    @Override
    public void registerListener(IOnNewBookArrivedListener l) throws RemoteException {
        mListeners.register(l);
    }

    @Override
    public void unregisterListener(IOnNewBookArrivedListener l) throws RemoteException {
        mListeners.unregister(l);
    }
}


