package org.gittner.osmbugs.activities;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;

import org.gittner.osmbugs.R;
import org.gittner.osmbugs.bugs.Bug;
import org.gittner.osmbugs.common.Comment;
import org.gittner.osmbugs.common.CommentAdapter;
import org.gittner.osmbugs.tasks.BugUpdateTask;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class BugEditorActivity extends SherlockActivity{

    public static int DIALOGEDITCOMMENT = 1;
    /* The Bug currently being edited */
    private Bug bug_;

    /* Used for passing the Bugs Position in the Buglist to this Intent */
    public static String EXTRABUG = "BUG";

    /* All Views on this Activity */
    private TextView txtvTitle_, txtvText_, txtvNewCommentHeader_, txtvNewComment_;
    private Spinner spnState_;
    private ListView lvComments_;
    private ArrayAdapter<String> stateAdapter_;
    private ArrayAdapter<Comment> commentAdapter_;

    /* The Main Menu */
    Menu menu_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Enable the Spinning Wheel for undetermined Progress */
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_bug_editor);

        /* For devices that use ActionBarSherlock the Indeterminate State has to be set to false
         * otherwise it will be displayed at start
         */
        setSupportProgressBarIndeterminate(false);
        setSupportProgressBarIndeterminateVisibility(false);
        setSupportProgressBarVisibility(false);

        /* Deparcel the current Bug */
        bug_ = getIntent().getParcelableExtra(EXTRABUG);

        /* Setup the Bug Icon */
        txtvTitle_ = (TextView) findViewById(R.id.textvTitle);
        txtvTitle_.setText(bug_.getTitle());
        Linkify.addLinks(txtvTitle_, Linkify.WEB_URLS);
        txtvTitle_.setText(Html.fromHtml(txtvTitle_.getText().toString()));

        /* Setup the Description EditText */
        txtvText_ = (TextView) findViewById(R.id.txtvText);
        txtvText_.setText(bug_.getSnippet());
        Linkify.addLinks(txtvText_, Linkify.WEB_URLS);
        txtvText_.setText(Html.fromHtml(txtvText_.getText().toString()));
        txtvText_.setMovementMethod(ScrollingMovementMethod.getInstance());

        /* Start to Download Extra Data if neccessary through an AsyncTask and
         * set the ListViews adapter Either after download or if no Download needed instantaneous */
        if(bug_.willRetrieveExtraData()) {
            new AsyncTask<Bug, Void, Bug>(){

                SherlockActivity activity_;

                public AsyncTask<Bug, Void, Bug> init(SherlockActivity activity) {
                    activity_ = activity;
                    return this;
                }

                @Override
                protected void onPreExecute() {
                    activity_.setSupportProgressBarIndeterminate(true);
                    activity_.setSupportProgressBarIndeterminateVisibility(true);
                }

                @Override
                protected Bug doInBackground(Bug... bug) {
                    bug[0].retrieveExtraData();
                    return bug[0];
                }

                @Override
                protected void onPostExecute(Bug bug) {
                    activity_.setSupportProgressBarIndeterminate(false);
                    activity_.setSupportProgressBarIndeterminateVisibility(false);
                    commentAdapter_ = new CommentAdapter(activity_, R.layout.comment_icon, bug_.getComments());
                    lvComments_ = (ListView) findViewById(R.id.listView1);
                    lvComments_.setAdapter(commentAdapter_);
                    commentAdapter_.notifyDataSetChanged();
                }}.init(this).execute(bug_);
        }
        else{
            commentAdapter_ = new CommentAdapter(this, R.layout.comment_icon, bug_.getComments());
            lvComments_ = (ListView) findViewById(R.id.listView1);
            lvComments_.setAdapter(commentAdapter_);
        }

        /* Setup the new Comment Textview */
        txtvNewCommentHeader_ = (TextView) findViewById(R.id.txtvNewCommentHeader);
        txtvNewCommentHeader_.setVisibility(View.GONE);

        txtvNewComment_ = (TextView) findViewById(R.id.txtvNewComment);
        txtvNewComment_.setVisibility(View.GONE);
        txtvNewComment_.setText("");

        /* Setup the State Spinner */
        spnState_ = (Spinner) findViewById(R.id.spnState);

        stateAdapter_ = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        spnState_.setAdapter(stateAdapter_);

        if(bug_.getState() == Bug.STATE.OPEN || bug_.isReopenable())
            stateAdapter_.add(bug_.getStringFromState(this, Bug.STATE.OPEN));

        if(bug_.getState() == Bug.STATE.CLOSED || bug_.isClosable())
            stateAdapter_.add(bug_.getStringFromState(this, Bug.STATE.CLOSED));

        if(bug_.getState() == Bug.STATE.IGNORED || bug_.isIgnorable())
            stateAdapter_.add(bug_.getStringFromState(this, Bug.STATE.IGNORED));

        spnState_.setSelection(stateAdapter_.getPosition(bug_.getStringFromState(this, bug_.getState())));

        stateAdapter_.notifyDataSetChanged();
    }

    @Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu_ = menu;
        getSupportMenuInflater().inflate(R.menu.bug_editor, menu);

        update();
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_cancel){
            setResult(SherlockActivity.RESULT_CANCELED);
            finish();

            return true;
        }
        else if(item.getItemId() == R.id.action_save){
            /* Save the new Bug state */
            bug_.setState(bug_.getStateFromString(this, stateAdapter_.getItem(spnState_.getSelectedItemPosition())));

            new BugUpdateTask(this).execute(bug_);

            return true;
        }
        else if(item.getItemId() == R.id.action_edit){
            showDialog(DIALOGEDITCOMMENT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    @SuppressWarnings("deprecation")
    public Dialog onCreateDialog(int id) {

        if(id == DIALOGEDITCOMMENT){
            /* Create a simple Dialog where the Comment can be changed */
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            final EditText commentEditText = new EditText(this);
            commentEditText.setText(bug_.getNewComment());

            builder.setView(commentEditText);

            builder.setMessage(getString(R.string.comment));
            builder.setPositiveButton(getString(R.string.ok), new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    bug_.setNewComment(commentEditText.getText().toString());
                    txtvNewComment_.setText(commentEditText.getText().toString());
                    dialog.dismiss();
                    update();
                }});
            builder.setNegativeButton(getString(R.string.cancel), new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }});

            return builder.create();
        }

        return super.onCreateDialog(id);
    }

    private void update() {
        /* View or hide the New Comment TextViews */
        if(bug_.hasNewComment()) {
            txtvNewComment_.setVisibility(View.VISIBLE);
            txtvNewCommentHeader_.setVisibility(View.VISIBLE);
        }
        else{
            txtvNewComment_.setVisibility(View.GONE);
            txtvNewCommentHeader_.setVisibility(View.GONE);
        }

        /* Deactivate the Edit Entry if needed */
        if(!bug_.isCommentable())
            menu_.findItem(R.id.action_edit).setVisible(false);
        else
            menu_.findItem(R.id.action_edit).setVisible(true);

        //TODO: Turn only on when the bug is actually commitable i.e. has a comment and changed State */
        /* View or hide the Save Icon */
        if(bug_.hasNewComment() || bug_.hasNewState())
            menu_.findItem(R.id.action_save).setVisible(true);
        else
            menu_.findItem(R.id.action_save).setVisible(false);
    }
}
