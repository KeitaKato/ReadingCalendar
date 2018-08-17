package com.example.eiga_.readingcalendar.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eiga_.readingcalendar.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link OCRPreviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OCRPreviewFragment extends Fragment {
    private String OCRData;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    public OCRPreviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OCRPreviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OCRPreviewFragment newInstance(String OCRData) {
        OCRPreviewFragment fragment = new OCRPreviewFragment();
        Bundle args = new Bundle();
        args.putString("OCR_DATA", OCRData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        OCRData = args.getString("OCR_DATA");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ocr_preview, null);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.fragment_ocr_constraintlayout);

        TextView OCRPreview = view.findViewById(R.id.tess_two_test);
        OCRPreview.setText(OCRData);
        return view;

    }
}
