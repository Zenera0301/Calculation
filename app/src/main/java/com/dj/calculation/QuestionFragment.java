package com.dj.calculation;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dj.calculation.databinding.FragmentQuestionBinding;
import com.dj.calculation.databinding.FragmentTitleBinding;

public class QuestionFragment extends Fragment {

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel myViewModel;
        myViewModel = ViewModelProviders.of(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(), this)).get(MyViewModel.class);
        myViewModel.generator();
        myViewModel.getCurrentScore().setValue(0);
        FragmentQuestionBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        StringBuilder sb = new StringBuilder();
        View.OnClickListener listener = new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.button0:
                        sb.append(0);
                        break;
                    case R.id.button1:
                        sb.append(1);
                        break;
                    case R.id.button2:
                        sb.append(2);
                        break;
                    case R.id.button3:
                        sb.append(3);
                        break;
                    case R.id.button4:
                        sb.append(4);
                        break;
                    case R.id.button5:
                        sb.append(5);
                        break;
                    case R.id.button6:
                        sb.append(6);
                        break;
                    case R.id.button7:
                        sb.append(7);
                        break;
                    case R.id.button8:
                        sb.append(8);
                        break;
                    case R.id.button9:
                        sb.append(9);
                        break;
                    case R.id.buttonClear:
                        sb.setLength(0);
                        break;
                }
                if(sb.length()==0){
                    binding.textView9.setText(getString(R.string.input_indicator));
                }else{
                    binding.textView9.setText(sb.toString());
                }
            }
        };
        binding.button0.setOnClickListener(listener);
        binding.button1.setOnClickListener(listener);
        binding.button2.setOnClickListener(listener);
        binding.button3.setOnClickListener(listener);
        binding.button4.setOnClickListener(listener);
        binding.button5.setOnClickListener(listener);
        binding.button6.setOnClickListener(listener);
        binding.button7.setOnClickListener(listener);
        binding.button8.setOnClickListener(listener);
        binding.button9.setOnClickListener(listener);
        binding.buttonClear.setOnClickListener(listener);
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sb.length() == 0) {
                    sb.append("-1");
                }
                if(Integer.valueOf(sb.toString()).intValue() == myViewModel.getAnswer().getValue()){
                    myViewModel.answerCorrect();
                    sb.setLength(0);
                    binding.textView9.setText(R.string.answer_correct_message);
                }else{
                    NavController controller = Navigation.findNavController(v);
                    if(myViewModel.winFlag){
                        controller.navigate(R.id.action_questionFragment_to_winFragment);
                        myViewModel.winFlag = false;
                        myViewModel.save();
                    }else{
                        controller.navigate(R.id.action_questionFragment_to_loseFragment);
                    }
                }
            }
        });
        return binding.getRoot();
    }

}













































