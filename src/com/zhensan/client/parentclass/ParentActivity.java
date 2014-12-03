package com.zhensan.client.parentclass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


public abstract class ParentActivity extends Activity {

		
	/**
	 * LOG��ӡ��ǩ
	 */
	// private static final String TAG = ParentActivity.class.getSimpleName();

	/**
	 * ȫ�ֵ�Context {@link #mContext = this.getApplicationContext();}
	 */
	protected Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int layoutId = getLayoutId();
		if (layoutId == 0) {
		} else {
			setContentView(layoutId);
			// ɾ�����ڱ���
			getWindow().setBackgroundDrawable(null);
		}

		mContext = this.getApplicationContext();

//		if (loadingDialog == null) {
////			loadingDialog = new LoadingDialog(this, clickListener);
//		}
		// ���û�չʾ��Ϣǰ��׼����������������ﴦ��
		preliminary();
	}

	private OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
//			switch (view.getId()) {
////			case R.id.loadingdialog_cancel_ImageView:
////				loadingDialog.cancel();
//				break;
//
//			default:
//				break;
//			}

		}
	};
	
	/**
	 * ���û�չʾ��Ϣǰ��׼����������������ﴦ��
	 */
	protected void preliminary() {
		// ��ʼ�����
		setupViews();

		// ��ʼ������
		initialized();
	}

	/**
	 * ��ȡȫ�ֵ�Context
	 * 
	 * @return {@link #mContext = this.getApplicationContext();}
	 */
	public Context getContext() {
		return mContext;
	}

	/**
	 * �����ļ�ID
	 * 
	 * @return
	 */
	protected abstract int getLayoutId();

	/**
	 * ��ʼ�����
	 */
	protected abstract void setupViews();

	/**
	 * ��ʼ������
	 */
	protected abstract void initialized();

	/**
	 * ��Ҫ�̳߳ز���������
	 * 
	 */
	protected abstract void threadTask();
	
	/**
	 * ��ʱ����ʾToast��ʾ(����String)
	 * 
	 * @param message
	 */
	protected void showToast(String message) {
		Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * ��ʱ����ʾToast��ʾ(����res)
	 * 
	 * @param resId
	 */
	protected void showToast(int resId) {
		Toast.makeText(mContext, getString(resId), Toast.LENGTH_LONG).show();
	}

	/**
	 * ������ʾToast��ʾ(����res)
	 * 
	 * @param resId
	 */
	protected void showShortToast(int resId) {
		Toast.makeText(mContext, getString(resId), Toast.LENGTH_SHORT).show();
	}

	/**
	 * ������ʾToast��ʾ(����String)
	 * 
	 * @param text
	 *            .
	 */
	protected void showShortToast(String text) {
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * ͨ��Class��ת����
	 **/
	public void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/**
	 * ����Bundleͨ��Class��ת����
	 **/
	public void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		// overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/**
	 * ͨ��Action��ת����
	 **/
	public void startActivity(String action) {
		startActivity(action, null);
	}

	/**
	 * ����Bundleͨ��Action��ת����
	 **/
	public void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		// overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/**
	 * ����Bundleͨ��Class�򿪱༭����
	 **/
	public void startActivityForResult(Class<?> cls, Bundle bundle,
			int requestCode) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent, requestCode);
		// overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/**
	 * �����ҽ��ҳ��������˳�
	 */
	@Override
	public void finish() {
		super.finish();
		// overridePendingTransition(R.anim.push_right_in,
		// R.anim.push_right_out);
	}

	/**
	 * Ĭ���˳�
	 */
	public void defaultFinish() {
		super.finish();
	}

	}

