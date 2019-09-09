package com.wan7451.binderpool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binder 连接池
        final BinderPool binderPool = BinderPool.getInstance(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
                ICompute compute = ICompute.Stub.asInterface(computeBinder);
                try {
                    compute.add(10,10);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }


                IBinder bookManagerBinder = binderPool.queryBinder(BinderPool.BINDER_BOOK_MANAGER);
                IBookManager bookManager = IBookManager.Stub.asInterface(bookManagerBinder);
                //Binder 回调
                try {
                    bookManager.registerListener(new OnNewBookArrivedListenerImpl(){

                        @Override
                        public void onNewBookArrived(Book book) throws RemoteException {
                            Log.i("=====",book.bookName);
                            Log.i("=====",Thread.currentThread().getName());

                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    bookManager.addBook(new Book(10,"haoah"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        },1000);

    }
}
