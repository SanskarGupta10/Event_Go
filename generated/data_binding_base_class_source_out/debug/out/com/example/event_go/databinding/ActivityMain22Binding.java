// Generated by view binder compiler. Do not edit!
package com.example.event_go.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.event_go.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMain22Binding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button b222;

  @NonNull
  public final Button b223;

  @NonNull
  public final Button b231;

  @NonNull
  public final EditText ed221;

  @NonNull
  public final TextView tv221;

  @NonNull
  public final TextView tv222;

  @NonNull
  public final TextView tv223;

  @NonNull
  public final TextView tv224;

  @NonNull
  public final TextView tv225;

  private ActivityMain22Binding(@NonNull ConstraintLayout rootView, @NonNull Button b222,
      @NonNull Button b223, @NonNull Button b231, @NonNull EditText ed221, @NonNull TextView tv221,
      @NonNull TextView tv222, @NonNull TextView tv223, @NonNull TextView tv224,
      @NonNull TextView tv225) {
    this.rootView = rootView;
    this.b222 = b222;
    this.b223 = b223;
    this.b231 = b231;
    this.ed221 = ed221;
    this.tv221 = tv221;
    this.tv222 = tv222;
    this.tv223 = tv223;
    this.tv224 = tv224;
    this.tv225 = tv225;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMain22Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMain22Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main22, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMain22Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.b222;
      Button b222 = ViewBindings.findChildViewById(rootView, id);
      if (b222 == null) {
        break missingId;
      }

      id = R.id.b223;
      Button b223 = ViewBindings.findChildViewById(rootView, id);
      if (b223 == null) {
        break missingId;
      }

      id = R.id.b231;
      Button b231 = ViewBindings.findChildViewById(rootView, id);
      if (b231 == null) {
        break missingId;
      }

      id = R.id.ed221;
      EditText ed221 = ViewBindings.findChildViewById(rootView, id);
      if (ed221 == null) {
        break missingId;
      }

      id = R.id.tv221;
      TextView tv221 = ViewBindings.findChildViewById(rootView, id);
      if (tv221 == null) {
        break missingId;
      }

      id = R.id.tv222;
      TextView tv222 = ViewBindings.findChildViewById(rootView, id);
      if (tv222 == null) {
        break missingId;
      }

      id = R.id.tv223;
      TextView tv223 = ViewBindings.findChildViewById(rootView, id);
      if (tv223 == null) {
        break missingId;
      }

      id = R.id.tv224;
      TextView tv224 = ViewBindings.findChildViewById(rootView, id);
      if (tv224 == null) {
        break missingId;
      }

      id = R.id.tv225;
      TextView tv225 = ViewBindings.findChildViewById(rootView, id);
      if (tv225 == null) {
        break missingId;
      }

      return new ActivityMain22Binding((ConstraintLayout) rootView, b222, b223, b231, ed221, tv221,
          tv222, tv223, tv224, tv225);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
