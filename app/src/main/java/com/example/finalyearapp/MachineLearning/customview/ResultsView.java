
package com.example.finalyearapp.MachineLearning.customview;


import com.example.finalyearapp.MachineLearning.tflite.Classifier;

import java.util.List;

public interface ResultsView {
  public void setResults(final List<Classifier.Recognition> results);
}
