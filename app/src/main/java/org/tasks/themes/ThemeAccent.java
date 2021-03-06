package org.tasks.themes;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import org.tasks.R;
import org.tasks.dialogs.ColorPalettePicker.Pickable;

public class ThemeAccent implements Pickable {

  public static final int[] ACCENTS =
      new int[] {
        R.style.BlueGreyAccent,
        R.style.RedAccent,
        R.style.PinkAccent,
        R.style.PurpleAccent,
        R.style.DeepPurpleAccent,
        R.style.IndigoAccent,
        R.style.BlueAccent,
        R.style.LightBlueAccent,
        R.style.CyanAccent,
        R.style.TealAccent,
        R.style.GreenAccent,
        R.style.LightGreenAccent,
        R.style.LimeAccent,
        R.style.YellowAccent,
        R.style.AmberAccent,
        R.style.OrangeAccent,
        R.style.DeepOrangeAccent
      };
  public static final Parcelable.Creator<ThemeAccent> CREATOR =
      new Parcelable.Creator<ThemeAccent>() {
        @Override
        public ThemeAccent createFromParcel(Parcel source) {
          return new ThemeAccent(source);
        }

        @Override
        public ThemeAccent[] newArray(int size) {
          return new ThemeAccent[size];
        }
      };
  private final int style;
  @Deprecated private final int accentColor;

  public ThemeAccent(Context context, int index) {
    this.style = ACCENTS[index];
    Resources.Theme theme = new ContextThemeWrapper(context, ThemeAccent.ACCENTS[index]).getTheme();
    this.accentColor = resolveAttribute(theme, R.attr.colorSecondary);
  }

  private ThemeAccent(Parcel source) {
    style = source.readInt();
    accentColor = source.readInt();
  }

  private static int resolveAttribute(Resources.Theme theme, int attribute) {
    TypedValue typedValue = new TypedValue();
    theme.resolveAttribute(attribute, typedValue, true);
    return typedValue.data;
  }

  public void applyStyle(Resources.Theme theme) {
    theme.applyStyle(style, true);
  }

  @Override
  public int getPickerColor() {
    return accentColor;
  }

  @Override
  public boolean isFree() {
    switch (style) {
      case R.style.BlueGreyAccent:
      case R.style.RedAccent:
        return true;
      default:
        return false;
    }
  }

  @Deprecated
  public int getAccentColor() {
    return accentColor;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(style);
    dest.writeInt(accentColor);
  }
}
