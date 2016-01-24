// Generated code from Butter Knife. Do not modify!
package com.learning.games;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FlagQuizGame$$ViewBinder<T extends com.learning.games.FlagQuizGame> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230721, "field 'questionNumberTextView'");
    target.questionNumberTextView = finder.castView(view, 2131230721, "field 'questionNumberTextView'");
    view = finder.findRequiredView(source, 2131230724, "field 'buttonTableLayout'");
    target.buttonTableLayout = finder.castView(view, 2131230724, "field 'buttonTableLayout'");
    view = finder.findRequiredView(source, 2131230722, "field 'flagImageView'");
    target.flagImageView = finder.castView(view, 2131230722, "field 'flagImageView'");
    view = finder.findRequiredView(source, 2131230729, "field 'resultBox'");
    target.resultBox = finder.castView(view, 2131230729, "field 'resultBox'");
  }

  @Override public void unbind(T target) {
    target.questionNumberTextView = null;
    target.buttonTableLayout = null;
    target.flagImageView = null;
    target.resultBox = null;
  }
}
