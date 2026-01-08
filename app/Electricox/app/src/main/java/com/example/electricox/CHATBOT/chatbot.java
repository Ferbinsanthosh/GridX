package com.example.electricox.CHATBOT;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;


import com.example.electricox.R;

import java.util.List;

public class chatbot extends AppCompatActivity {

    private QADao qaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        qaDao = new QADao(getApplicationContext());

        qaDao.insertQA("Hello", "Hi there!");
        qaDao.insertQA("Bye,Bie", "See you later! If you ever need advice or just want to chat, I'll be here. Stay fit and happy, goodbye!");
        qaDao.insertQA("How are you?", "I'm fine, thank you!");
        qaDao.insertQA("Hi", "Hi there!");
        qaDao.insertQA("What is Your Name ", "I am a simple chatbot!!");
        qaDao.insertQA("What is a First Information Report (FIR)?", "An FIR is a document prepared by the police when they receive information about a cognizable offense. It's the first step to start an investigation.");
        qaDao.insertQA("How do I file an FIR?", "You can file an FIR by visiting your local police station. Provide all details of the incident, and ensure to keep a copy of the FIR for reference.");
        qaDao.insertQA("What is bail?", "Bail is the temporary release of an accused person from custody, usually under a bond or surety. Bail ensures that the person will appear in court for their trial.");
        qaDao.insertQA("What is a summons?", "A summons is a legal document issued by a court requiring someone to attend court or respond to a legal complaint.");
        qaDao.insertQA("What is the difference between civil and criminal cases?", "Civil cases involve private disputes between individuals or organizations, while criminal cases involve actions considered harmful to society, prosecuted by the state.");
        qaDao.insertQA("What should I do if I'm arrested?", "Stay calm, remember your right to remain silent, and ask for a lawyer. It's essential to have legal representation to protect your rights.");
        qaDao.insertQA("What is the role of a public prosecutor?", "A public prosecutor is an official who represents the state in criminal cases. They present evidence against the accused to secure a conviction.");
        qaDao.insertQA("Can I represent myself in court?", "Yes, you can represent yourself in court, known as being a ‘pro se’ litigant. However, it’s recommended to have a lawyer, especially for complex cases.");
        qaDao.insertQA("What is cross-examination?", "Cross-examination is the questioning of a witness by the opposing party in a trial to challenge the testimony and uncover the truth.");
        qaDao.insertQA("How do I apply for a restraining order?", "You can apply for a restraining order by filing a petition in your local court, providing evidence of harassment or abuse. A lawyer can assist with the process.");
        qaDao.insertQA("What is contempt of court?", "Contempt of court is any behavior that disrespects or disobeys the authority of the court, potentially leading to penalties.");
        qaDao.insertQA("What is the burden of proof in a criminal case?", "The burden of proof in a criminal case lies with the prosecution, who must prove the defendant's guilt ‘beyond a reasonable doubt.’");
        qaDao.insertQA("How can I report police misconduct?", "You can report police misconduct by submitting a complaint to the police department’s internal affairs division or an external oversight agency.");
        qaDao.insertQA("What are my rights during a police search?", "You have the right to ask for a search warrant. If the police have no warrant, you can ask why the search is being conducted and whether you’re free to leave.");
        qaDao.insertQA("What is plea bargaining?", "Plea bargaining is a process where the defendant and prosecutor agree to a lesser charge or reduced sentence in exchange for a guilty plea.");
        qaDao.insertQA("How can I check the status of my court case?", "You can check your case status by visiting the official website of your state judiciary and using the case tracking feature.");
        qaDao.insertQA("What happens in a preliminary hearing?", "In a preliminary hearing, the judge determines if there’s enough evidence to proceed with a trial. It’s the initial step in criminal proceedings.");
        qaDao.insertQA("What is a parole?", "Parole is the early release of a prisoner before the full sentence is served, subject to good behavior and adherence to specific conditions.");
        qaDao.insertQA("What is the statute of limitations?", "The statute of limitations is the maximum time after an event that legal proceedings may be initiated. Different cases have different limitation periods.");
    
        EditText inputEditText = findViewById(R.id.inputEditText);
        findViewById(R.id.sendButton).setOnClickListener(v -> {
            String userInput = inputEditText.getText().toString().trim().toLowerCase();
            String chatbotResponse = getResponse(userInput);

            // Add user message
            addUserMessage(userInput);

            // Add chatbot message
            addChatbotMessage(chatbotResponse);

            // Clear the input field
            inputEditText.setText("");
        });
    }

    private void addUserMessage(String message) {
        LinearLayout chatContainer = findViewById(R.id.chatContainer);
        CardView userCard = createUserCard(message);
        chatContainer.addView(userCard);
    }

    private void addChatbotMessage(String message) {
        LinearLayout chatContainer = findViewById(R.id.chatContainer);
        CardView chatbotCard = createChatbotCard(message);
        chatContainer.addView(chatbotCard);
    }

    private CardView createUserCard(String message) {
        CardView cardView = new CardView(this);
        cardView.setLayoutParams(createUserCardLayoutParams()); // Switched to user layout params
        cardView.setCardBackgroundColor(getResources().getColor(R.color.myPrimary));
        cardView.setRadius(16); // Set radius for rounded corners
        cardView.setCardElevation(8); // Set elevation for a shadow effect

        TextView labelTextView = createLabelTextView("User:");
        TextView textView = createMessageTextView(message);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(labelTextView);
        linearLayout.addView(textView);

        cardView.addView(linearLayout);

        return cardView;
    }

    private CardView createChatbotCard(String message) {
        CardView cardView = new CardView(this);
        cardView.setLayoutParams(createChatbotCardLayoutParams()); // Switched to chatbot layout params
        cardView.setCardBackgroundColor(getResources().getColor(R.color.black));
        cardView.setRadius(16); // Set radius for rounded corners
        cardView.setCardElevation(8); // Set elevation for a shadow effect

        TextView labelTextView = createLabelTextView("Bot:");
        TextView textView = createMessageTextView(message);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(labelTextView);
        linearLayout.addView(textView);

        cardView.addView(linearLayout);

        return cardView;
    }

    private TextView createLabelTextView(String label) {
        TextView textView = new TextView(this);
        textView.setText(label);
        textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
        textView.setTextSize(18); // Set the font size
        textView.setPadding(16, 0, 16, 4); // Adjust padding as needed
        textView.setTypeface(ResourcesCompat.getFont(this, R.font.quicksand_medium)); // Set the font family
        return textView;
    }

    private TextView createMessageTextView(String message) {
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setTextColor(getResources().getColor(android.R.color.white));
        textView.setTextSize(20); // Set the font size
        textView.setPadding(16, 4, 16, 16); // Adjust padding as needed
        textView.setTypeface(ResourcesCompat.getFont(this, R.font.quicksand_medium)); // Set the font family
        return textView;
    }


    private LinearLayout.LayoutParams createUserCardLayoutParams() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                (int) (getResources().getDisplayMetrics().widthPixels * 0.85),
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(30, 8, 0, 8);
        return layoutParams;
    }

    private LinearLayout.LayoutParams createChatbotCardLayoutParams() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                (int) (getResources().getDisplayMetrics().widthPixels * 0.85),
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 8, 30, 8);
        return layoutParams;
    }

    private String getResponse(String userQuery) {
        List<QAModel> qaList = qaDao.getAllQA();
        for (QAModel qaModel : qaList) {
            String question = qaModel.getQuestion().toLowerCase();
            if (userQuery.contains(question) || question.contains(userQuery)) {
                return qaModel.getAnswer();
            }
        }

        return "I'm a simple chatbot. Ask me anything!";
    }
}
