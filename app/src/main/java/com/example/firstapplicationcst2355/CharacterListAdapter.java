package com.example.firstapplicationcst2355;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class CharacterListAdapter extends BaseAdapter {

    private Context context;
    private List<String> characterNames;

    public CharacterListAdapter(Context context, List<String> characterNames) {
        this.context = context;
        this.characterNames = characterNames;
    }

    @Override
    public int getCount() {
        return characterNames.size();
    }

    @Override
    public Object getItem(int position) {
        return characterNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_character, parent, false);
        }

        //set the character name
        TextView characterNameTextView = convertView.findViewById(R.id.characterNameTextView);
        characterNameTextView.setText(characterNames.get(position));

        return convertView;
    }
}
