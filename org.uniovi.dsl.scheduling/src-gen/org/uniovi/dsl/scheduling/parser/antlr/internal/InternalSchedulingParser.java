package org.uniovi.dsl.scheduling.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.uniovi.dsl.scheduling.services.SchedulingGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSchedulingParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INSTALLATIONS_HEADER", "RULE_INSTALLATION_TYPE", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_MAINTENANCES_HEADER", "RULE_MAINTENANCE_TYPE", "RULE_OPTIONS_HEADER", "RULE_FLOAT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Name:'", "'Capacity:'", "'Interval:'", "'Periods:'", "'Priority:'", "'Includes:'", "'Installations:'", "'Input:'", "'Random_state:'", "'Prob_before_restriction:'", "'Rate_of_change:'", "'Days_per_period:'", "'Number_of_periods:'", "'Extra_hours:'", "'Iterations:'", "'Start_date:'", "','", "'-'", "'/'"
    };
    public static final int RULE_OPTIONS_HEADER=11;
    public static final int RULE_STRING=7;
    public static final int RULE_SL_COMMENT=14;
    public static final int T__19=19;
    public static final int T__17=17;
    public static final int RULE_MAINTENANCES_HEADER=9;
    public static final int T__18=18;
    public static final int T__33=33;
    public static final int RULE_INSTALLATION_TYPE=5;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=6;
    public static final int RULE_WS=15;
    public static final int RULE_ANY_OTHER=16;
    public static final int RULE_MAINTENANCE_TYPE=10;
    public static final int RULE_INSTALLATIONS_HEADER=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=8;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=13;
    public static final int T__23=23;
    public static final int RULE_FLOAT=12;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalSchedulingParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSchedulingParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSchedulingParser.tokenNames; }
    public String getGrammarFileName() { return "InternalScheduling.g"; }



     	private SchedulingGrammarAccess grammarAccess;

        public InternalSchedulingParser(TokenStream input, SchedulingGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Program";
       	}

       	@Override
       	protected SchedulingGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleProgram"
    // InternalScheduling.g:64:1: entryRuleProgram returns [EObject current=null] : iv_ruleProgram= ruleProgram EOF ;
    public final EObject entryRuleProgram() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProgram = null;


        try {
            // InternalScheduling.g:64:48: (iv_ruleProgram= ruleProgram EOF )
            // InternalScheduling.g:65:2: iv_ruleProgram= ruleProgram EOF
            {
             newCompositeNode(grammarAccess.getProgramRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProgram=ruleProgram();

            state._fsp--;

             current =iv_ruleProgram; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProgram"


    // $ANTLR start "ruleProgram"
    // InternalScheduling.g:71:1: ruleProgram returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) ) ) )+ {...}?) ) ) ;
    public final EObject ruleProgram() throws RecognitionException {
        EObject current = null;

        EObject lv_installations_1_0 = null;

        EObject lv_maintenances_2_0 = null;

        EObject lv_options_3_0 = null;



        	enterRule();

        try {
            // InternalScheduling.g:77:2: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) ) ) )+ {...}?) ) ) )
            // InternalScheduling.g:78:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) ) ) )+ {...}?) ) )
            {
            // InternalScheduling.g:78:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) ) ) )+ {...}?) ) )
            // InternalScheduling.g:79:3: ( ( ( ({...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) ) ) )+ {...}?) )
            {
            // InternalScheduling.g:79:3: ( ( ( ({...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) ) ) )+ {...}?) )
            // InternalScheduling.g:80:4: ( ( ({...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) ) ) )+ {...}?)
            {
             
            			  getUnorderedGroupHelper().enter(grammarAccess.getProgramAccess().getUnorderedGroup());
            			
            // InternalScheduling.g:83:4: ( ( ({...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) ) ) )+ {...}?)
            // InternalScheduling.g:84:5: ( ({...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) ) ) )+ {...}?
            {
            // InternalScheduling.g:84:5: ( ({...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) ) ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=4;
                int LA1_0 = input.LA(1);

                if ( LA1_0 == RULE_INSTALLATIONS_HEADER && getUnorderedGroupHelper().canSelect(grammarAccess.getProgramAccess().getUnorderedGroup(), 0) ) {
                    alt1=1;
                }
                else if ( LA1_0 == RULE_MAINTENANCES_HEADER && getUnorderedGroupHelper().canSelect(grammarAccess.getProgramAccess().getUnorderedGroup(), 1) ) {
                    alt1=2;
                }
                else if ( LA1_0 == RULE_OPTIONS_HEADER && getUnorderedGroupHelper().canSelect(grammarAccess.getProgramAccess().getUnorderedGroup(), 2) ) {
                    alt1=3;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalScheduling.g:85:3: ({...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) ) )
            	    {
            	    // InternalScheduling.g:85:3: ({...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) ) )
            	    // InternalScheduling.g:86:4: {...}? => ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProgramAccess().getUnorderedGroup(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleProgram", "getUnorderedGroupHelper().canSelect(grammarAccess.getProgramAccess().getUnorderedGroup(), 0)");
            	    }
            	    // InternalScheduling.g:86:101: ( ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) ) )
            	    // InternalScheduling.g:87:5: ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getProgramAccess().getUnorderedGroup(), 0);
            	    				
            	    // InternalScheduling.g:90:8: ({...}? => ( (lv_installations_1_0= ruleInstallations ) ) )
            	    // InternalScheduling.g:90:9: {...}? => ( (lv_installations_1_0= ruleInstallations ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProgram", "true");
            	    }
            	    // InternalScheduling.g:90:18: ( (lv_installations_1_0= ruleInstallations ) )
            	    // InternalScheduling.g:90:19: (lv_installations_1_0= ruleInstallations )
            	    {
            	    // InternalScheduling.g:90:19: (lv_installations_1_0= ruleInstallations )
            	    // InternalScheduling.g:91:9: lv_installations_1_0= ruleInstallations
            	    {

            	    									newCompositeNode(grammarAccess.getProgramAccess().getInstallationsInstallationsParserRuleCall_0_0());
            	    								
            	    pushFollow(FOLLOW_3);
            	    lv_installations_1_0=ruleInstallations();

            	    state._fsp--;


            	    									if (current==null) {
            	    										current = createModelElementForParent(grammarAccess.getProgramRule());
            	    									}
            	    									set(
            	    										current,
            	    										"installations",
            	    										lv_installations_1_0,
            	    										"org.uniovi.dsl.scheduling.Scheduling.Installations");
            	    									afterParserOrEnumRuleCall();
            	    								

            	    }


            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProgramAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalScheduling.g:113:3: ({...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) ) )
            	    {
            	    // InternalScheduling.g:113:3: ({...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) ) )
            	    // InternalScheduling.g:114:4: {...}? => ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProgramAccess().getUnorderedGroup(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleProgram", "getUnorderedGroupHelper().canSelect(grammarAccess.getProgramAccess().getUnorderedGroup(), 1)");
            	    }
            	    // InternalScheduling.g:114:101: ( ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) ) )
            	    // InternalScheduling.g:115:5: ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getProgramAccess().getUnorderedGroup(), 1);
            	    				
            	    // InternalScheduling.g:118:8: ({...}? => ( (lv_maintenances_2_0= ruleMaintenances ) ) )
            	    // InternalScheduling.g:118:9: {...}? => ( (lv_maintenances_2_0= ruleMaintenances ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProgram", "true");
            	    }
            	    // InternalScheduling.g:118:18: ( (lv_maintenances_2_0= ruleMaintenances ) )
            	    // InternalScheduling.g:118:19: (lv_maintenances_2_0= ruleMaintenances )
            	    {
            	    // InternalScheduling.g:118:19: (lv_maintenances_2_0= ruleMaintenances )
            	    // InternalScheduling.g:119:9: lv_maintenances_2_0= ruleMaintenances
            	    {

            	    									newCompositeNode(grammarAccess.getProgramAccess().getMaintenancesMaintenancesParserRuleCall_1_0());
            	    								
            	    pushFollow(FOLLOW_3);
            	    lv_maintenances_2_0=ruleMaintenances();

            	    state._fsp--;


            	    									if (current==null) {
            	    										current = createModelElementForParent(grammarAccess.getProgramRule());
            	    									}
            	    									set(
            	    										current,
            	    										"maintenances",
            	    										lv_maintenances_2_0,
            	    										"org.uniovi.dsl.scheduling.Scheduling.Maintenances");
            	    									afterParserOrEnumRuleCall();
            	    								

            	    }


            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProgramAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalScheduling.g:141:3: ({...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) ) )
            	    {
            	    // InternalScheduling.g:141:3: ({...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) ) )
            	    // InternalScheduling.g:142:4: {...}? => ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getProgramAccess().getUnorderedGroup(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleProgram", "getUnorderedGroupHelper().canSelect(grammarAccess.getProgramAccess().getUnorderedGroup(), 2)");
            	    }
            	    // InternalScheduling.g:142:101: ( ({...}? => ( (lv_options_3_0= ruleConfig ) ) ) )
            	    // InternalScheduling.g:143:5: ({...}? => ( (lv_options_3_0= ruleConfig ) ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getProgramAccess().getUnorderedGroup(), 2);
            	    				
            	    // InternalScheduling.g:146:8: ({...}? => ( (lv_options_3_0= ruleConfig ) ) )
            	    // InternalScheduling.g:146:9: {...}? => ( (lv_options_3_0= ruleConfig ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleProgram", "true");
            	    }
            	    // InternalScheduling.g:146:18: ( (lv_options_3_0= ruleConfig ) )
            	    // InternalScheduling.g:146:19: (lv_options_3_0= ruleConfig )
            	    {
            	    // InternalScheduling.g:146:19: (lv_options_3_0= ruleConfig )
            	    // InternalScheduling.g:147:9: lv_options_3_0= ruleConfig
            	    {

            	    									newCompositeNode(grammarAccess.getProgramAccess().getOptionsConfigParserRuleCall_2_0());
            	    								
            	    pushFollow(FOLLOW_3);
            	    lv_options_3_0=ruleConfig();

            	    state._fsp--;


            	    									if (current==null) {
            	    										current = createModelElementForParent(grammarAccess.getProgramRule());
            	    									}
            	    									set(
            	    										current,
            	    										"options",
            	    										lv_options_3_0,
            	    										"org.uniovi.dsl.scheduling.Scheduling.Config");
            	    									afterParserOrEnumRuleCall();
            	    								

            	    }


            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getProgramAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getProgramAccess().getUnorderedGroup()) ) {
                throw new FailedPredicateException(input, "ruleProgram", "getUnorderedGroupHelper().canLeave(grammarAccess.getProgramAccess().getUnorderedGroup())");
            }

            }


            }

             
            			  getUnorderedGroupHelper().leave(grammarAccess.getProgramAccess().getUnorderedGroup());
            			

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProgram"


    // $ANTLR start "entryRuleInstallations"
    // InternalScheduling.g:180:1: entryRuleInstallations returns [EObject current=null] : iv_ruleInstallations= ruleInstallations EOF ;
    public final EObject entryRuleInstallations() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInstallations = null;


        try {
            // InternalScheduling.g:180:54: (iv_ruleInstallations= ruleInstallations EOF )
            // InternalScheduling.g:181:2: iv_ruleInstallations= ruleInstallations EOF
            {
             newCompositeNode(grammarAccess.getInstallationsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInstallations=ruleInstallations();

            state._fsp--;

             current =iv_ruleInstallations; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInstallations"


    // $ANTLR start "ruleInstallations"
    // InternalScheduling.g:187:1: ruleInstallations returns [EObject current=null] : (this_INSTALLATIONS_HEADER_0= RULE_INSTALLATIONS_HEADER ( (lv_installations_1_0= ruleInstallationDef ) )+ ) ;
    public final EObject ruleInstallations() throws RecognitionException {
        EObject current = null;

        Token this_INSTALLATIONS_HEADER_0=null;
        EObject lv_installations_1_0 = null;



        	enterRule();

        try {
            // InternalScheduling.g:193:2: ( (this_INSTALLATIONS_HEADER_0= RULE_INSTALLATIONS_HEADER ( (lv_installations_1_0= ruleInstallationDef ) )+ ) )
            // InternalScheduling.g:194:2: (this_INSTALLATIONS_HEADER_0= RULE_INSTALLATIONS_HEADER ( (lv_installations_1_0= ruleInstallationDef ) )+ )
            {
            // InternalScheduling.g:194:2: (this_INSTALLATIONS_HEADER_0= RULE_INSTALLATIONS_HEADER ( (lv_installations_1_0= ruleInstallationDef ) )+ )
            // InternalScheduling.g:195:3: this_INSTALLATIONS_HEADER_0= RULE_INSTALLATIONS_HEADER ( (lv_installations_1_0= ruleInstallationDef ) )+
            {
            this_INSTALLATIONS_HEADER_0=(Token)match(input,RULE_INSTALLATIONS_HEADER,FOLLOW_4); 

            			newLeafNode(this_INSTALLATIONS_HEADER_0, grammarAccess.getInstallationsAccess().getINSTALLATIONS_HEADERTerminalRuleCall_0());
            		
            // InternalScheduling.g:199:3: ( (lv_installations_1_0= ruleInstallationDef ) )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_INSTALLATION_TYPE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalScheduling.g:200:4: (lv_installations_1_0= ruleInstallationDef )
            	    {
            	    // InternalScheduling.g:200:4: (lv_installations_1_0= ruleInstallationDef )
            	    // InternalScheduling.g:201:5: lv_installations_1_0= ruleInstallationDef
            	    {

            	    					newCompositeNode(grammarAccess.getInstallationsAccess().getInstallationsInstallationDefParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_5);
            	    lv_installations_1_0=ruleInstallationDef();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getInstallationsRule());
            	    					}
            	    					add(
            	    						current,
            	    						"installations",
            	    						lv_installations_1_0,
            	    						"org.uniovi.dsl.scheduling.Scheduling.InstallationDef");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInstallations"


    // $ANTLR start "entryRuleInstallationDef"
    // InternalScheduling.g:222:1: entryRuleInstallationDef returns [EObject current=null] : iv_ruleInstallationDef= ruleInstallationDef EOF ;
    public final EObject entryRuleInstallationDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInstallationDef = null;


        try {
            // InternalScheduling.g:222:56: (iv_ruleInstallationDef= ruleInstallationDef EOF )
            // InternalScheduling.g:223:2: iv_ruleInstallationDef= ruleInstallationDef EOF
            {
             newCompositeNode(grammarAccess.getInstallationDefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInstallationDef=ruleInstallationDef();

            state._fsp--;

             current =iv_ruleInstallationDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInstallationDef"


    // $ANTLR start "ruleInstallationDef"
    // InternalScheduling.g:229:1: ruleInstallationDef returns [EObject current=null] : (this_INSTALLATION_TYPE_0= RULE_INSTALLATION_TYPE ( (lv_id_1_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) ) )+ {...}?) ) ) ) ;
    public final EObject ruleInstallationDef() throws RecognitionException {
        EObject current = null;

        Token this_INSTALLATION_TYPE_0=null;
        Token lv_id_1_0=null;
        Token otherlv_3=null;
        Token lv_installationName_4_0=null;
        Token otherlv_5=null;
        Token lv_capacity_6_0=null;


        	enterRule();

        try {
            // InternalScheduling.g:235:2: ( (this_INSTALLATION_TYPE_0= RULE_INSTALLATION_TYPE ( (lv_id_1_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) ) )+ {...}?) ) ) ) )
            // InternalScheduling.g:236:2: (this_INSTALLATION_TYPE_0= RULE_INSTALLATION_TYPE ( (lv_id_1_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) ) )+ {...}?) ) ) )
            {
            // InternalScheduling.g:236:2: (this_INSTALLATION_TYPE_0= RULE_INSTALLATION_TYPE ( (lv_id_1_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) ) )+ {...}?) ) ) )
            // InternalScheduling.g:237:3: this_INSTALLATION_TYPE_0= RULE_INSTALLATION_TYPE ( (lv_id_1_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) ) )+ {...}?) ) )
            {
            this_INSTALLATION_TYPE_0=(Token)match(input,RULE_INSTALLATION_TYPE,FOLLOW_6); 

            			newLeafNode(this_INSTALLATION_TYPE_0, grammarAccess.getInstallationDefAccess().getINSTALLATION_TYPETerminalRuleCall_0());
            		
            // InternalScheduling.g:241:3: ( (lv_id_1_0= RULE_ID ) )
            // InternalScheduling.g:242:4: (lv_id_1_0= RULE_ID )
            {
            // InternalScheduling.g:242:4: (lv_id_1_0= RULE_ID )
            // InternalScheduling.g:243:5: lv_id_1_0= RULE_ID
            {
            lv_id_1_0=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(lv_id_1_0, grammarAccess.getInstallationDefAccess().getIdIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getInstallationDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"id",
            						lv_id_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalScheduling.g:259:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) ) )+ {...}?) ) )
            // InternalScheduling.g:260:4: ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) ) )+ {...}?) )
            {
            // InternalScheduling.g:260:4: ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) ) )+ {...}?) )
            // InternalScheduling.g:261:5: ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) ) )+ {...}?)
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2());
            				
            // InternalScheduling.g:264:5: ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) ) )+ {...}?)
            // InternalScheduling.g:265:6: ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) ) )+ {...}?
            {
            // InternalScheduling.g:265:6: ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) ) )+
            int cnt3=0;
            loop3:
            do {
                int alt3=3;
                int LA3_0 = input.LA(1);

                if ( LA3_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2(), 0) ) {
                    alt3=1;
                }
                else if ( LA3_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2(), 1) ) {
                    alt3=2;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalScheduling.g:266:4: ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:266:4: ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) ) )
            	    // InternalScheduling.g:267:5: {...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleInstallationDef", "getUnorderedGroupHelper().canSelect(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2(), 0)");
            	    }
            	    // InternalScheduling.g:267:112: ( ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) ) )
            	    // InternalScheduling.g:268:6: ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2(), 0);
            	    					
            	    // InternalScheduling.g:271:9: ({...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) ) )
            	    // InternalScheduling.g:271:10: {...}? => (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleInstallationDef", "true");
            	    }
            	    // InternalScheduling.g:271:19: (otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) ) )
            	    // InternalScheduling.g:271:20: otherlv_3= 'Name:' ( (lv_installationName_4_0= RULE_STRING ) )
            	    {
            	    otherlv_3=(Token)match(input,17,FOLLOW_8); 

            	    									newLeafNode(otherlv_3, grammarAccess.getInstallationDefAccess().getNameKeyword_2_0_0());
            	    								
            	    // InternalScheduling.g:275:9: ( (lv_installationName_4_0= RULE_STRING ) )
            	    // InternalScheduling.g:276:10: (lv_installationName_4_0= RULE_STRING )
            	    {
            	    // InternalScheduling.g:276:10: (lv_installationName_4_0= RULE_STRING )
            	    // InternalScheduling.g:277:11: lv_installationName_4_0= RULE_STRING
            	    {
            	    lv_installationName_4_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

            	    											newLeafNode(lv_installationName_4_0, grammarAccess.getInstallationDefAccess().getInstallationNameSTRINGTerminalRuleCall_2_0_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getInstallationDefRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"installationName",
            	    												lv_installationName_4_0,
            	    												"org.eclipse.xtext.common.Terminals.STRING");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalScheduling.g:299:4: ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:299:4: ({...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) ) )
            	    // InternalScheduling.g:300:5: {...}? => ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleInstallationDef", "getUnorderedGroupHelper().canSelect(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2(), 1)");
            	    }
            	    // InternalScheduling.g:300:112: ( ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) ) )
            	    // InternalScheduling.g:301:6: ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2(), 1);
            	    					
            	    // InternalScheduling.g:304:9: ({...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) ) )
            	    // InternalScheduling.g:304:10: {...}? => (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleInstallationDef", "true");
            	    }
            	    // InternalScheduling.g:304:19: (otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) ) )
            	    // InternalScheduling.g:304:20: otherlv_5= 'Capacity:' ( (lv_capacity_6_0= RULE_INT ) )
            	    {
            	    otherlv_5=(Token)match(input,18,FOLLOW_10); 

            	    									newLeafNode(otherlv_5, grammarAccess.getInstallationDefAccess().getCapacityKeyword_2_1_0());
            	    								
            	    // InternalScheduling.g:308:9: ( (lv_capacity_6_0= RULE_INT ) )
            	    // InternalScheduling.g:309:10: (lv_capacity_6_0= RULE_INT )
            	    {
            	    // InternalScheduling.g:309:10: (lv_capacity_6_0= RULE_INT )
            	    // InternalScheduling.g:310:11: lv_capacity_6_0= RULE_INT
            	    {
            	    lv_capacity_6_0=(Token)match(input,RULE_INT,FOLLOW_9); 

            	    											newLeafNode(lv_capacity_6_0, grammarAccess.getInstallationDefAccess().getCapacityINTTerminalRuleCall_2_1_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getInstallationDefRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"capacity",
            	    												lv_capacity_6_0,
            	    												"org.eclipse.xtext.common.Terminals.INT");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2()) ) {
                throw new FailedPredicateException(input, "ruleInstallationDef", "getUnorderedGroupHelper().canLeave(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2())");
            }

            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getInstallationDefAccess().getUnorderedGroup_2());
            				

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInstallationDef"


    // $ANTLR start "entryRuleMaintenances"
    // InternalScheduling.g:344:1: entryRuleMaintenances returns [EObject current=null] : iv_ruleMaintenances= ruleMaintenances EOF ;
    public final EObject entryRuleMaintenances() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMaintenances = null;


        try {
            // InternalScheduling.g:344:53: (iv_ruleMaintenances= ruleMaintenances EOF )
            // InternalScheduling.g:345:2: iv_ruleMaintenances= ruleMaintenances EOF
            {
             newCompositeNode(grammarAccess.getMaintenancesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMaintenances=ruleMaintenances();

            state._fsp--;

             current =iv_ruleMaintenances; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMaintenances"


    // $ANTLR start "ruleMaintenances"
    // InternalScheduling.g:351:1: ruleMaintenances returns [EObject current=null] : (this_MAINTENANCES_HEADER_0= RULE_MAINTENANCES_HEADER ( (lv_maints_1_0= ruleMaintDef ) )+ ) ;
    public final EObject ruleMaintenances() throws RecognitionException {
        EObject current = null;

        Token this_MAINTENANCES_HEADER_0=null;
        EObject lv_maints_1_0 = null;



        	enterRule();

        try {
            // InternalScheduling.g:357:2: ( (this_MAINTENANCES_HEADER_0= RULE_MAINTENANCES_HEADER ( (lv_maints_1_0= ruleMaintDef ) )+ ) )
            // InternalScheduling.g:358:2: (this_MAINTENANCES_HEADER_0= RULE_MAINTENANCES_HEADER ( (lv_maints_1_0= ruleMaintDef ) )+ )
            {
            // InternalScheduling.g:358:2: (this_MAINTENANCES_HEADER_0= RULE_MAINTENANCES_HEADER ( (lv_maints_1_0= ruleMaintDef ) )+ )
            // InternalScheduling.g:359:3: this_MAINTENANCES_HEADER_0= RULE_MAINTENANCES_HEADER ( (lv_maints_1_0= ruleMaintDef ) )+
            {
            this_MAINTENANCES_HEADER_0=(Token)match(input,RULE_MAINTENANCES_HEADER,FOLLOW_11); 

            			newLeafNode(this_MAINTENANCES_HEADER_0, grammarAccess.getMaintenancesAccess().getMAINTENANCES_HEADERTerminalRuleCall_0());
            		
            // InternalScheduling.g:363:3: ( (lv_maints_1_0= ruleMaintDef ) )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==RULE_MAINTENANCE_TYPE) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalScheduling.g:364:4: (lv_maints_1_0= ruleMaintDef )
            	    {
            	    // InternalScheduling.g:364:4: (lv_maints_1_0= ruleMaintDef )
            	    // InternalScheduling.g:365:5: lv_maints_1_0= ruleMaintDef
            	    {

            	    					newCompositeNode(grammarAccess.getMaintenancesAccess().getMaintsMaintDefParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_12);
            	    lv_maints_1_0=ruleMaintDef();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getMaintenancesRule());
            	    					}
            	    					add(
            	    						current,
            	    						"maints",
            	    						lv_maints_1_0,
            	    						"org.uniovi.dsl.scheduling.Scheduling.MaintDef");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMaintenances"


    // $ANTLR start "entryRuleMaintDef"
    // InternalScheduling.g:386:1: entryRuleMaintDef returns [EObject current=null] : iv_ruleMaintDef= ruleMaintDef EOF ;
    public final EObject entryRuleMaintDef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMaintDef = null;


        try {
            // InternalScheduling.g:386:49: (iv_ruleMaintDef= ruleMaintDef EOF )
            // InternalScheduling.g:387:2: iv_ruleMaintDef= ruleMaintDef EOF
            {
             newCompositeNode(grammarAccess.getMaintDefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMaintDef=ruleMaintDef();

            state._fsp--;

             current =iv_ruleMaintDef; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMaintDef"


    // $ANTLR start "ruleMaintDef"
    // InternalScheduling.g:393:1: ruleMaintDef returns [EObject current=null] : (this_MAINTENANCE_TYPE_0= RULE_MAINTENANCE_TYPE ( (lv_id_1_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) ) )+ {...}?) ) ) ) ;
    public final EObject ruleMaintDef() throws RecognitionException {
        EObject current = null;

        Token this_MAINTENANCE_TYPE_0=null;
        Token lv_id_1_0=null;
        Token otherlv_3=null;
        Token lv_maintName_4_0=null;
        Token otherlv_5=null;
        Token lv_hours_6_0=null;
        Token otherlv_7=null;
        Token lv_duration_8_0=null;
        Token otherlv_9=null;
        Token lv_priority_10_0=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        AntlrDatatypeRuleToken lv_includes_12_0 = null;

        AntlrDatatypeRuleToken lv_installations_14_0 = null;



        	enterRule();

        try {
            // InternalScheduling.g:399:2: ( (this_MAINTENANCE_TYPE_0= RULE_MAINTENANCE_TYPE ( (lv_id_1_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) ) )+ {...}?) ) ) ) )
            // InternalScheduling.g:400:2: (this_MAINTENANCE_TYPE_0= RULE_MAINTENANCE_TYPE ( (lv_id_1_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) ) )+ {...}?) ) ) )
            {
            // InternalScheduling.g:400:2: (this_MAINTENANCE_TYPE_0= RULE_MAINTENANCE_TYPE ( (lv_id_1_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) ) )+ {...}?) ) ) )
            // InternalScheduling.g:401:3: this_MAINTENANCE_TYPE_0= RULE_MAINTENANCE_TYPE ( (lv_id_1_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) ) )+ {...}?) ) )
            {
            this_MAINTENANCE_TYPE_0=(Token)match(input,RULE_MAINTENANCE_TYPE,FOLLOW_6); 

            			newLeafNode(this_MAINTENANCE_TYPE_0, grammarAccess.getMaintDefAccess().getMAINTENANCE_TYPETerminalRuleCall_0());
            		
            // InternalScheduling.g:405:3: ( (lv_id_1_0= RULE_ID ) )
            // InternalScheduling.g:406:4: (lv_id_1_0= RULE_ID )
            {
            // InternalScheduling.g:406:4: (lv_id_1_0= RULE_ID )
            // InternalScheduling.g:407:5: lv_id_1_0= RULE_ID
            {
            lv_id_1_0=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(lv_id_1_0, grammarAccess.getMaintDefAccess().getIdIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMaintDefRule());
            					}
            					setWithLastConsumed(
            						current,
            						"id",
            						lv_id_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalScheduling.g:423:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) ) )+ {...}?) ) )
            // InternalScheduling.g:424:4: ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) ) )+ {...}?) )
            {
            // InternalScheduling.g:424:4: ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) ) )+ {...}?) )
            // InternalScheduling.g:425:5: ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) ) )+ {...}?)
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getMaintDefAccess().getUnorderedGroup_2());
            				
            // InternalScheduling.g:428:5: ( ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) ) )+ {...}?)
            // InternalScheduling.g:429:6: ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) ) )+ {...}?
            {
            // InternalScheduling.g:429:6: ( ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) ) )+
            int cnt5=0;
            loop5:
            do {
                int alt5=7;
                int LA5_0 = input.LA(1);

                if ( LA5_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 0) ) {
                    alt5=1;
                }
                else if ( LA5_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 1) ) {
                    alt5=2;
                }
                else if ( LA5_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 2) ) {
                    alt5=3;
                }
                else if ( LA5_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 3) ) {
                    alt5=4;
                }
                else if ( LA5_0 == 22 && getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 4) ) {
                    alt5=5;
                }
                else if ( LA5_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 5) ) {
                    alt5=6;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalScheduling.g:430:4: ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:430:4: ({...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) ) )
            	    // InternalScheduling.g:431:5: {...}? => ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleMaintDef", "getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 0)");
            	    }
            	    // InternalScheduling.g:431:105: ( ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) ) )
            	    // InternalScheduling.g:432:6: ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 0);
            	    					
            	    // InternalScheduling.g:435:9: ({...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) ) )
            	    // InternalScheduling.g:435:10: {...}? => (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleMaintDef", "true");
            	    }
            	    // InternalScheduling.g:435:19: (otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) ) )
            	    // InternalScheduling.g:435:20: otherlv_3= 'Name:' ( (lv_maintName_4_0= RULE_STRING ) )
            	    {
            	    otherlv_3=(Token)match(input,17,FOLLOW_8); 

            	    									newLeafNode(otherlv_3, grammarAccess.getMaintDefAccess().getNameKeyword_2_0_0());
            	    								
            	    // InternalScheduling.g:439:9: ( (lv_maintName_4_0= RULE_STRING ) )
            	    // InternalScheduling.g:440:10: (lv_maintName_4_0= RULE_STRING )
            	    {
            	    // InternalScheduling.g:440:10: (lv_maintName_4_0= RULE_STRING )
            	    // InternalScheduling.g:441:11: lv_maintName_4_0= RULE_STRING
            	    {
            	    lv_maintName_4_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

            	    											newLeafNode(lv_maintName_4_0, grammarAccess.getMaintDefAccess().getMaintNameSTRINGTerminalRuleCall_2_0_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getMaintDefRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"maintName",
            	    												lv_maintName_4_0,
            	    												"org.eclipse.xtext.common.Terminals.STRING");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getMaintDefAccess().getUnorderedGroup_2());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalScheduling.g:463:4: ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:463:4: ({...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) ) )
            	    // InternalScheduling.g:464:5: {...}? => ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleMaintDef", "getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 1)");
            	    }
            	    // InternalScheduling.g:464:105: ( ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) ) )
            	    // InternalScheduling.g:465:6: ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 1);
            	    					
            	    // InternalScheduling.g:468:9: ({...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) ) )
            	    // InternalScheduling.g:468:10: {...}? => (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleMaintDef", "true");
            	    }
            	    // InternalScheduling.g:468:19: (otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) ) )
            	    // InternalScheduling.g:468:20: otherlv_5= 'Interval:' ( (lv_hours_6_0= RULE_INT ) )
            	    {
            	    otherlv_5=(Token)match(input,19,FOLLOW_10); 

            	    									newLeafNode(otherlv_5, grammarAccess.getMaintDefAccess().getIntervalKeyword_2_1_0());
            	    								
            	    // InternalScheduling.g:472:9: ( (lv_hours_6_0= RULE_INT ) )
            	    // InternalScheduling.g:473:10: (lv_hours_6_0= RULE_INT )
            	    {
            	    // InternalScheduling.g:473:10: (lv_hours_6_0= RULE_INT )
            	    // InternalScheduling.g:474:11: lv_hours_6_0= RULE_INT
            	    {
            	    lv_hours_6_0=(Token)match(input,RULE_INT,FOLLOW_14); 

            	    											newLeafNode(lv_hours_6_0, grammarAccess.getMaintDefAccess().getHoursINTTerminalRuleCall_2_1_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getMaintDefRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"hours",
            	    												lv_hours_6_0,
            	    												"org.eclipse.xtext.common.Terminals.INT");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getMaintDefAccess().getUnorderedGroup_2());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalScheduling.g:496:4: ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:496:4: ({...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) ) )
            	    // InternalScheduling.g:497:5: {...}? => ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleMaintDef", "getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 2)");
            	    }
            	    // InternalScheduling.g:497:105: ( ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) ) )
            	    // InternalScheduling.g:498:6: ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 2);
            	    					
            	    // InternalScheduling.g:501:9: ({...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) ) )
            	    // InternalScheduling.g:501:10: {...}? => (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleMaintDef", "true");
            	    }
            	    // InternalScheduling.g:501:19: (otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) ) )
            	    // InternalScheduling.g:501:20: otherlv_7= 'Periods:' ( (lv_duration_8_0= RULE_INT ) )
            	    {
            	    otherlv_7=(Token)match(input,20,FOLLOW_10); 

            	    									newLeafNode(otherlv_7, grammarAccess.getMaintDefAccess().getPeriodsKeyword_2_2_0());
            	    								
            	    // InternalScheduling.g:505:9: ( (lv_duration_8_0= RULE_INT ) )
            	    // InternalScheduling.g:506:10: (lv_duration_8_0= RULE_INT )
            	    {
            	    // InternalScheduling.g:506:10: (lv_duration_8_0= RULE_INT )
            	    // InternalScheduling.g:507:11: lv_duration_8_0= RULE_INT
            	    {
            	    lv_duration_8_0=(Token)match(input,RULE_INT,FOLLOW_14); 

            	    											newLeafNode(lv_duration_8_0, grammarAccess.getMaintDefAccess().getDurationINTTerminalRuleCall_2_2_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getMaintDefRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"duration",
            	    												lv_duration_8_0,
            	    												"org.eclipse.xtext.common.Terminals.INT");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getMaintDefAccess().getUnorderedGroup_2());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalScheduling.g:529:4: ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:529:4: ({...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) ) )
            	    // InternalScheduling.g:530:5: {...}? => ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleMaintDef", "getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 3)");
            	    }
            	    // InternalScheduling.g:530:105: ( ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) ) )
            	    // InternalScheduling.g:531:6: ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 3);
            	    					
            	    // InternalScheduling.g:534:9: ({...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) ) )
            	    // InternalScheduling.g:534:10: {...}? => (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleMaintDef", "true");
            	    }
            	    // InternalScheduling.g:534:19: (otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) ) )
            	    // InternalScheduling.g:534:20: otherlv_9= 'Priority:' ( (lv_priority_10_0= RULE_INT ) )
            	    {
            	    otherlv_9=(Token)match(input,21,FOLLOW_10); 

            	    									newLeafNode(otherlv_9, grammarAccess.getMaintDefAccess().getPriorityKeyword_2_3_0());
            	    								
            	    // InternalScheduling.g:538:9: ( (lv_priority_10_0= RULE_INT ) )
            	    // InternalScheduling.g:539:10: (lv_priority_10_0= RULE_INT )
            	    {
            	    // InternalScheduling.g:539:10: (lv_priority_10_0= RULE_INT )
            	    // InternalScheduling.g:540:11: lv_priority_10_0= RULE_INT
            	    {
            	    lv_priority_10_0=(Token)match(input,RULE_INT,FOLLOW_14); 

            	    											newLeafNode(lv_priority_10_0, grammarAccess.getMaintDefAccess().getPriorityINTTerminalRuleCall_2_3_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getMaintDefRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"priority",
            	    												lv_priority_10_0,
            	    												"org.eclipse.xtext.common.Terminals.INT");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getMaintDefAccess().getUnorderedGroup_2());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalScheduling.g:562:4: ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:562:4: ({...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) ) )
            	    // InternalScheduling.g:563:5: {...}? => ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleMaintDef", "getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 4)");
            	    }
            	    // InternalScheduling.g:563:105: ( ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) ) )
            	    // InternalScheduling.g:564:6: ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 4);
            	    					
            	    // InternalScheduling.g:567:9: ({...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) ) )
            	    // InternalScheduling.g:567:10: {...}? => (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleMaintDef", "true");
            	    }
            	    // InternalScheduling.g:567:19: (otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) ) )
            	    // InternalScheduling.g:567:20: otherlv_11= 'Includes:' ( (lv_includes_12_0= ruleList ) )
            	    {
            	    otherlv_11=(Token)match(input,22,FOLLOW_6); 

            	    									newLeafNode(otherlv_11, grammarAccess.getMaintDefAccess().getIncludesKeyword_2_4_0());
            	    								
            	    // InternalScheduling.g:571:9: ( (lv_includes_12_0= ruleList ) )
            	    // InternalScheduling.g:572:10: (lv_includes_12_0= ruleList )
            	    {
            	    // InternalScheduling.g:572:10: (lv_includes_12_0= ruleList )
            	    // InternalScheduling.g:573:11: lv_includes_12_0= ruleList
            	    {

            	    											newCompositeNode(grammarAccess.getMaintDefAccess().getIncludesListParserRuleCall_2_4_1_0());
            	    										
            	    pushFollow(FOLLOW_14);
            	    lv_includes_12_0=ruleList();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getMaintDefRule());
            	    											}
            	    											set(
            	    												current,
            	    												"includes",
            	    												lv_includes_12_0,
            	    												"org.uniovi.dsl.scheduling.Scheduling.List");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getMaintDefAccess().getUnorderedGroup_2());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalScheduling.g:596:4: ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:596:4: ({...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) ) )
            	    // InternalScheduling.g:597:5: {...}? => ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleMaintDef", "getUnorderedGroupHelper().canSelect(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 5)");
            	    }
            	    // InternalScheduling.g:597:105: ( ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) ) )
            	    // InternalScheduling.g:598:6: ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getMaintDefAccess().getUnorderedGroup_2(), 5);
            	    					
            	    // InternalScheduling.g:601:9: ({...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) ) )
            	    // InternalScheduling.g:601:10: {...}? => (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleMaintDef", "true");
            	    }
            	    // InternalScheduling.g:601:19: (otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) ) )
            	    // InternalScheduling.g:601:20: otherlv_13= 'Installations:' ( (lv_installations_14_0= ruleList ) )
            	    {
            	    otherlv_13=(Token)match(input,23,FOLLOW_6); 

            	    									newLeafNode(otherlv_13, grammarAccess.getMaintDefAccess().getInstallationsKeyword_2_5_0());
            	    								
            	    // InternalScheduling.g:605:9: ( (lv_installations_14_0= ruleList ) )
            	    // InternalScheduling.g:606:10: (lv_installations_14_0= ruleList )
            	    {
            	    // InternalScheduling.g:606:10: (lv_installations_14_0= ruleList )
            	    // InternalScheduling.g:607:11: lv_installations_14_0= ruleList
            	    {

            	    											newCompositeNode(grammarAccess.getMaintDefAccess().getInstallationsListParserRuleCall_2_5_1_0());
            	    										
            	    pushFollow(FOLLOW_14);
            	    lv_installations_14_0=ruleList();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getMaintDefRule());
            	    											}
            	    											set(
            	    												current,
            	    												"installations",
            	    												lv_installations_14_0,
            	    												"org.uniovi.dsl.scheduling.Scheduling.List");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getMaintDefAccess().getUnorderedGroup_2());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getMaintDefAccess().getUnorderedGroup_2()) ) {
                throw new FailedPredicateException(input, "ruleMaintDef", "getUnorderedGroupHelper().canLeave(grammarAccess.getMaintDefAccess().getUnorderedGroup_2())");
            }

            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getMaintDefAccess().getUnorderedGroup_2());
            				

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMaintDef"


    // $ANTLR start "entryRuleConfig"
    // InternalScheduling.g:642:1: entryRuleConfig returns [EObject current=null] : iv_ruleConfig= ruleConfig EOF ;
    public final EObject entryRuleConfig() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConfig = null;


        try {
            // InternalScheduling.g:642:47: (iv_ruleConfig= ruleConfig EOF )
            // InternalScheduling.g:643:2: iv_ruleConfig= ruleConfig EOF
            {
             newCompositeNode(grammarAccess.getConfigRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConfig=ruleConfig();

            state._fsp--;

             current =iv_ruleConfig; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConfig"


    // $ANTLR start "ruleConfig"
    // InternalScheduling.g:649:1: ruleConfig returns [EObject current=null] : (this_OPTIONS_HEADER_0= RULE_OPTIONS_HEADER ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+ {...}?) ) ) ) ;
    public final EObject ruleConfig() throws RecognitionException {
        EObject current = null;

        Token this_OPTIONS_HEADER_0=null;
        Token otherlv_2=null;
        Token lv_input_3_0=null;
        Token otherlv_4=null;
        Token lv_randomState_5_0=null;
        Token otherlv_6=null;
        Token lv_probBeforeScheduling_7_0=null;
        Token otherlv_8=null;
        Token lv_rateOfChange_9_0=null;
        Token otherlv_10=null;
        Token lv_daysPerPeriod_11_0=null;
        Token otherlv_12=null;
        Token lv_numberOfPeriods_13_0=null;
        Token otherlv_14=null;
        Token lv_extraHours_15_0=null;
        Token otherlv_16=null;
        Token lv_maxIters_17_0=null;
        Token otherlv_18=null;
        EObject lv_startDate_19_0 = null;



        	enterRule();

        try {
            // InternalScheduling.g:655:2: ( (this_OPTIONS_HEADER_0= RULE_OPTIONS_HEADER ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+ {...}?) ) ) ) )
            // InternalScheduling.g:656:2: (this_OPTIONS_HEADER_0= RULE_OPTIONS_HEADER ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+ {...}?) ) ) )
            {
            // InternalScheduling.g:656:2: (this_OPTIONS_HEADER_0= RULE_OPTIONS_HEADER ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+ {...}?) ) ) )
            // InternalScheduling.g:657:3: this_OPTIONS_HEADER_0= RULE_OPTIONS_HEADER ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+ {...}?) ) )
            {
            this_OPTIONS_HEADER_0=(Token)match(input,RULE_OPTIONS_HEADER,FOLLOW_15); 

            			newLeafNode(this_OPTIONS_HEADER_0, grammarAccess.getConfigAccess().getOPTIONS_HEADERTerminalRuleCall_0());
            		
            // InternalScheduling.g:661:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+ {...}?) ) )
            // InternalScheduling.g:662:4: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+ {...}?) )
            {
            // InternalScheduling.g:662:4: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+ {...}?) )
            // InternalScheduling.g:663:5: ( ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+ {...}?)
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getConfigAccess().getUnorderedGroup_1());
            				
            // InternalScheduling.g:666:5: ( ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+ {...}?)
            // InternalScheduling.g:667:6: ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+ {...}?
            {
            // InternalScheduling.g:667:6: ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+
            int cnt6=0;
            loop6:
            do {
                int alt6=10;
                alt6 = dfa6.predict(input);
                switch (alt6) {
            	case 1 :
            	    // InternalScheduling.g:668:4: ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:668:4: ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) )
            	    // InternalScheduling.g:669:5: {...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 0)");
            	    }
            	    // InternalScheduling.g:669:103: ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) )
            	    // InternalScheduling.g:670:6: ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 0);
            	    					
            	    // InternalScheduling.g:673:9: ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) )
            	    // InternalScheduling.g:673:10: {...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "true");
            	    }
            	    // InternalScheduling.g:673:19: (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) )
            	    // InternalScheduling.g:673:20: otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) )
            	    {
            	    otherlv_2=(Token)match(input,24,FOLLOW_8); 

            	    									newLeafNode(otherlv_2, grammarAccess.getConfigAccess().getInputKeyword_1_0_0());
            	    								
            	    // InternalScheduling.g:677:9: ( (lv_input_3_0= RULE_STRING ) )
            	    // InternalScheduling.g:678:10: (lv_input_3_0= RULE_STRING )
            	    {
            	    // InternalScheduling.g:678:10: (lv_input_3_0= RULE_STRING )
            	    // InternalScheduling.g:679:11: lv_input_3_0= RULE_STRING
            	    {
            	    lv_input_3_0=(Token)match(input,RULE_STRING,FOLLOW_16); 

            	    											newLeafNode(lv_input_3_0, grammarAccess.getConfigAccess().getInputSTRINGTerminalRuleCall_1_0_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getConfigRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"input",
            	    												lv_input_3_0,
            	    												"org.eclipse.xtext.common.Terminals.STRING");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalScheduling.g:701:4: ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:701:4: ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) )
            	    // InternalScheduling.g:702:5: {...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 1)");
            	    }
            	    // InternalScheduling.g:702:103: ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) )
            	    // InternalScheduling.g:703:6: ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 1);
            	    					
            	    // InternalScheduling.g:706:9: ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) )
            	    // InternalScheduling.g:706:10: {...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "true");
            	    }
            	    // InternalScheduling.g:706:19: (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) )
            	    // InternalScheduling.g:706:20: otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) )
            	    {
            	    otherlv_4=(Token)match(input,25,FOLLOW_10); 

            	    									newLeafNode(otherlv_4, grammarAccess.getConfigAccess().getRandom_stateKeyword_1_1_0());
            	    								
            	    // InternalScheduling.g:710:9: ( (lv_randomState_5_0= RULE_INT ) )
            	    // InternalScheduling.g:711:10: (lv_randomState_5_0= RULE_INT )
            	    {
            	    // InternalScheduling.g:711:10: (lv_randomState_5_0= RULE_INT )
            	    // InternalScheduling.g:712:11: lv_randomState_5_0= RULE_INT
            	    {
            	    lv_randomState_5_0=(Token)match(input,RULE_INT,FOLLOW_16); 

            	    											newLeafNode(lv_randomState_5_0, grammarAccess.getConfigAccess().getRandomStateINTTerminalRuleCall_1_1_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getConfigRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"randomState",
            	    												lv_randomState_5_0,
            	    												"org.eclipse.xtext.common.Terminals.INT");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalScheduling.g:734:4: ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:734:4: ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) )
            	    // InternalScheduling.g:735:5: {...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 2)");
            	    }
            	    // InternalScheduling.g:735:103: ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) )
            	    // InternalScheduling.g:736:6: ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 2);
            	    					
            	    // InternalScheduling.g:739:9: ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) )
            	    // InternalScheduling.g:739:10: {...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "true");
            	    }
            	    // InternalScheduling.g:739:19: (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) )
            	    // InternalScheduling.g:739:20: otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) )
            	    {
            	    otherlv_6=(Token)match(input,26,FOLLOW_17); 

            	    									newLeafNode(otherlv_6, grammarAccess.getConfigAccess().getProb_before_restrictionKeyword_1_2_0());
            	    								
            	    // InternalScheduling.g:743:9: ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) )
            	    // InternalScheduling.g:744:10: (lv_probBeforeScheduling_7_0= RULE_FLOAT )
            	    {
            	    // InternalScheduling.g:744:10: (lv_probBeforeScheduling_7_0= RULE_FLOAT )
            	    // InternalScheduling.g:745:11: lv_probBeforeScheduling_7_0= RULE_FLOAT
            	    {
            	    lv_probBeforeScheduling_7_0=(Token)match(input,RULE_FLOAT,FOLLOW_16); 

            	    											newLeafNode(lv_probBeforeScheduling_7_0, grammarAccess.getConfigAccess().getProbBeforeSchedulingFLOATTerminalRuleCall_1_2_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getConfigRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"probBeforeScheduling",
            	    												lv_probBeforeScheduling_7_0,
            	    												"org.uniovi.dsl.scheduling.Scheduling.FLOAT");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalScheduling.g:767:4: ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:767:4: ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) )
            	    // InternalScheduling.g:768:5: {...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 3)");
            	    }
            	    // InternalScheduling.g:768:103: ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) )
            	    // InternalScheduling.g:769:6: ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 3);
            	    					
            	    // InternalScheduling.g:772:9: ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) )
            	    // InternalScheduling.g:772:10: {...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "true");
            	    }
            	    // InternalScheduling.g:772:19: (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) )
            	    // InternalScheduling.g:772:20: otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) )
            	    {
            	    otherlv_8=(Token)match(input,27,FOLLOW_17); 

            	    									newLeafNode(otherlv_8, grammarAccess.getConfigAccess().getRate_of_changeKeyword_1_3_0());
            	    								
            	    // InternalScheduling.g:776:9: ( (lv_rateOfChange_9_0= RULE_FLOAT ) )
            	    // InternalScheduling.g:777:10: (lv_rateOfChange_9_0= RULE_FLOAT )
            	    {
            	    // InternalScheduling.g:777:10: (lv_rateOfChange_9_0= RULE_FLOAT )
            	    // InternalScheduling.g:778:11: lv_rateOfChange_9_0= RULE_FLOAT
            	    {
            	    lv_rateOfChange_9_0=(Token)match(input,RULE_FLOAT,FOLLOW_16); 

            	    											newLeafNode(lv_rateOfChange_9_0, grammarAccess.getConfigAccess().getRateOfChangeFLOATTerminalRuleCall_1_3_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getConfigRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"rateOfChange",
            	    												lv_rateOfChange_9_0,
            	    												"org.uniovi.dsl.scheduling.Scheduling.FLOAT");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalScheduling.g:800:4: ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:800:4: ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) )
            	    // InternalScheduling.g:801:5: {...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 4)");
            	    }
            	    // InternalScheduling.g:801:103: ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) )
            	    // InternalScheduling.g:802:6: ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 4);
            	    					
            	    // InternalScheduling.g:805:9: ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) )
            	    // InternalScheduling.g:805:10: {...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "true");
            	    }
            	    // InternalScheduling.g:805:19: (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) )
            	    // InternalScheduling.g:805:20: otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) )
            	    {
            	    otherlv_10=(Token)match(input,28,FOLLOW_10); 

            	    									newLeafNode(otherlv_10, grammarAccess.getConfigAccess().getDays_per_periodKeyword_1_4_0());
            	    								
            	    // InternalScheduling.g:809:9: ( (lv_daysPerPeriod_11_0= RULE_INT ) )
            	    // InternalScheduling.g:810:10: (lv_daysPerPeriod_11_0= RULE_INT )
            	    {
            	    // InternalScheduling.g:810:10: (lv_daysPerPeriod_11_0= RULE_INT )
            	    // InternalScheduling.g:811:11: lv_daysPerPeriod_11_0= RULE_INT
            	    {
            	    lv_daysPerPeriod_11_0=(Token)match(input,RULE_INT,FOLLOW_16); 

            	    											newLeafNode(lv_daysPerPeriod_11_0, grammarAccess.getConfigAccess().getDaysPerPeriodINTTerminalRuleCall_1_4_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getConfigRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"daysPerPeriod",
            	    												lv_daysPerPeriod_11_0,
            	    												"org.eclipse.xtext.common.Terminals.INT");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalScheduling.g:833:4: ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:833:4: ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) )
            	    // InternalScheduling.g:834:5: {...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 5)");
            	    }
            	    // InternalScheduling.g:834:103: ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) )
            	    // InternalScheduling.g:835:6: ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 5);
            	    					
            	    // InternalScheduling.g:838:9: ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) )
            	    // InternalScheduling.g:838:10: {...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "true");
            	    }
            	    // InternalScheduling.g:838:19: (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) )
            	    // InternalScheduling.g:838:20: otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) )
            	    {
            	    otherlv_12=(Token)match(input,29,FOLLOW_10); 

            	    									newLeafNode(otherlv_12, grammarAccess.getConfigAccess().getNumber_of_periodsKeyword_1_5_0());
            	    								
            	    // InternalScheduling.g:842:9: ( (lv_numberOfPeriods_13_0= RULE_INT ) )
            	    // InternalScheduling.g:843:10: (lv_numberOfPeriods_13_0= RULE_INT )
            	    {
            	    // InternalScheduling.g:843:10: (lv_numberOfPeriods_13_0= RULE_INT )
            	    // InternalScheduling.g:844:11: lv_numberOfPeriods_13_0= RULE_INT
            	    {
            	    lv_numberOfPeriods_13_0=(Token)match(input,RULE_INT,FOLLOW_16); 

            	    											newLeafNode(lv_numberOfPeriods_13_0, grammarAccess.getConfigAccess().getNumberOfPeriodsINTTerminalRuleCall_1_5_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getConfigRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"numberOfPeriods",
            	    												lv_numberOfPeriods_13_0,
            	    												"org.eclipse.xtext.common.Terminals.INT");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 7 :
            	    // InternalScheduling.g:866:4: ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:866:4: ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) )
            	    // InternalScheduling.g:867:5: {...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 6) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 6)");
            	    }
            	    // InternalScheduling.g:867:103: ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) )
            	    // InternalScheduling.g:868:6: ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 6);
            	    					
            	    // InternalScheduling.g:871:9: ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) )
            	    // InternalScheduling.g:871:10: {...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "true");
            	    }
            	    // InternalScheduling.g:871:19: (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) )
            	    // InternalScheduling.g:871:20: otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) )
            	    {
            	    otherlv_14=(Token)match(input,30,FOLLOW_10); 

            	    									newLeafNode(otherlv_14, grammarAccess.getConfigAccess().getExtra_hoursKeyword_1_6_0());
            	    								
            	    // InternalScheduling.g:875:9: ( (lv_extraHours_15_0= RULE_INT ) )
            	    // InternalScheduling.g:876:10: (lv_extraHours_15_0= RULE_INT )
            	    {
            	    // InternalScheduling.g:876:10: (lv_extraHours_15_0= RULE_INT )
            	    // InternalScheduling.g:877:11: lv_extraHours_15_0= RULE_INT
            	    {
            	    lv_extraHours_15_0=(Token)match(input,RULE_INT,FOLLOW_16); 

            	    											newLeafNode(lv_extraHours_15_0, grammarAccess.getConfigAccess().getExtraHoursINTTerminalRuleCall_1_6_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getConfigRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"extraHours",
            	    												lv_extraHours_15_0,
            	    												"org.eclipse.xtext.common.Terminals.INT");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 8 :
            	    // InternalScheduling.g:899:4: ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:899:4: ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) )
            	    // InternalScheduling.g:900:5: {...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 7) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 7)");
            	    }
            	    // InternalScheduling.g:900:103: ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) )
            	    // InternalScheduling.g:901:6: ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 7);
            	    					
            	    // InternalScheduling.g:904:9: ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) )
            	    // InternalScheduling.g:904:10: {...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "true");
            	    }
            	    // InternalScheduling.g:904:19: (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) )
            	    // InternalScheduling.g:904:20: otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) )
            	    {
            	    otherlv_16=(Token)match(input,31,FOLLOW_10); 

            	    									newLeafNode(otherlv_16, grammarAccess.getConfigAccess().getIterationsKeyword_1_7_0());
            	    								
            	    // InternalScheduling.g:908:9: ( (lv_maxIters_17_0= RULE_INT ) )
            	    // InternalScheduling.g:909:10: (lv_maxIters_17_0= RULE_INT )
            	    {
            	    // InternalScheduling.g:909:10: (lv_maxIters_17_0= RULE_INT )
            	    // InternalScheduling.g:910:11: lv_maxIters_17_0= RULE_INT
            	    {
            	    lv_maxIters_17_0=(Token)match(input,RULE_INT,FOLLOW_16); 

            	    											newLeafNode(lv_maxIters_17_0, grammarAccess.getConfigAccess().getMaxItersINTTerminalRuleCall_1_7_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getConfigRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"maxIters",
            	    												lv_maxIters_17_0,
            	    												"org.eclipse.xtext.common.Terminals.INT");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 9 :
            	    // InternalScheduling.g:932:4: ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) )
            	    {
            	    // InternalScheduling.g:932:4: ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) )
            	    // InternalScheduling.g:933:5: {...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 8) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 8)");
            	    }
            	    // InternalScheduling.g:933:103: ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) )
            	    // InternalScheduling.g:934:6: ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 8);
            	    					
            	    // InternalScheduling.g:937:9: ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) )
            	    // InternalScheduling.g:937:10: {...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConfig", "true");
            	    }
            	    // InternalScheduling.g:937:19: (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) )
            	    // InternalScheduling.g:937:20: otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) )
            	    {
            	    otherlv_18=(Token)match(input,32,FOLLOW_10); 

            	    									newLeafNode(otherlv_18, grammarAccess.getConfigAccess().getStart_dateKeyword_1_8_0());
            	    								
            	    // InternalScheduling.g:941:9: ( (lv_startDate_19_0= ruleDMYDate ) )
            	    // InternalScheduling.g:942:10: (lv_startDate_19_0= ruleDMYDate )
            	    {
            	    // InternalScheduling.g:942:10: (lv_startDate_19_0= ruleDMYDate )
            	    // InternalScheduling.g:943:11: lv_startDate_19_0= ruleDMYDate
            	    {

            	    											newCompositeNode(grammarAccess.getConfigAccess().getStartDateDMYDateParserRuleCall_1_8_1_0());
            	    										
            	    pushFollow(FOLLOW_16);
            	    lv_startDate_19_0=ruleDMYDate();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getConfigRule());
            	    											}
            	    											set(
            	    												current,
            	    												"startDate",
            	    												lv_startDate_19_0,
            	    												"org.uniovi.dsl.scheduling.Scheduling.DMYDate");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigAccess().getUnorderedGroup_1());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getConfigAccess().getUnorderedGroup_1()) ) {
                throw new FailedPredicateException(input, "ruleConfig", "getUnorderedGroupHelper().canLeave(grammarAccess.getConfigAccess().getUnorderedGroup_1())");
            }

            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getConfigAccess().getUnorderedGroup_1());
            				

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConfig"


    // $ANTLR start "entryRuleList"
    // InternalScheduling.g:978:1: entryRuleList returns [String current=null] : iv_ruleList= ruleList EOF ;
    public final String entryRuleList() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleList = null;


        try {
            // InternalScheduling.g:978:44: (iv_ruleList= ruleList EOF )
            // InternalScheduling.g:979:2: iv_ruleList= ruleList EOF
            {
             newCompositeNode(grammarAccess.getListRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleList=ruleList();

            state._fsp--;

             current =iv_ruleList.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleList"


    // $ANTLR start "ruleList"
    // InternalScheduling.g:985:1: ruleList returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= ',' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleList() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalScheduling.g:991:2: ( (this_ID_0= RULE_ID (kw= ',' this_ID_2= RULE_ID )* ) )
            // InternalScheduling.g:992:2: (this_ID_0= RULE_ID (kw= ',' this_ID_2= RULE_ID )* )
            {
            // InternalScheduling.g:992:2: (this_ID_0= RULE_ID (kw= ',' this_ID_2= RULE_ID )* )
            // InternalScheduling.g:993:3: this_ID_0= RULE_ID (kw= ',' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_18); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getListAccess().getIDTerminalRuleCall_0());
            		
            // InternalScheduling.g:1000:3: (kw= ',' this_ID_2= RULE_ID )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==33) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalScheduling.g:1001:4: kw= ',' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,33,FOLLOW_6); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getListAccess().getCommaKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_18); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getListAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleList"


    // $ANTLR start "entryRuleDMYDate"
    // InternalScheduling.g:1018:1: entryRuleDMYDate returns [EObject current=null] : iv_ruleDMYDate= ruleDMYDate EOF ;
    public final EObject entryRuleDMYDate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDMYDate = null;


        try {
            // InternalScheduling.g:1018:48: (iv_ruleDMYDate= ruleDMYDate EOF )
            // InternalScheduling.g:1019:2: iv_ruleDMYDate= ruleDMYDate EOF
            {
             newCompositeNode(grammarAccess.getDMYDateRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDMYDate=ruleDMYDate();

            state._fsp--;

             current =iv_ruleDMYDate; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDMYDate"


    // $ANTLR start "ruleDMYDate"
    // InternalScheduling.g:1025:1: ruleDMYDate returns [EObject current=null] : ( ( ( (lv_day_0_0= RULE_INT ) ) otherlv_1= '-' ( (lv_month_2_0= RULE_INT ) ) otherlv_3= '-' ( (lv_year_4_0= RULE_INT ) ) ) | ( ( (lv_day_5_0= RULE_INT ) ) otherlv_6= '/' ( (lv_month_7_0= RULE_INT ) ) otherlv_8= '/' ( (lv_year_9_0= RULE_INT ) ) ) ) ;
    public final EObject ruleDMYDate() throws RecognitionException {
        EObject current = null;

        Token lv_day_0_0=null;
        Token otherlv_1=null;
        Token lv_month_2_0=null;
        Token otherlv_3=null;
        Token lv_year_4_0=null;
        Token lv_day_5_0=null;
        Token otherlv_6=null;
        Token lv_month_7_0=null;
        Token otherlv_8=null;
        Token lv_year_9_0=null;


        	enterRule();

        try {
            // InternalScheduling.g:1031:2: ( ( ( ( (lv_day_0_0= RULE_INT ) ) otherlv_1= '-' ( (lv_month_2_0= RULE_INT ) ) otherlv_3= '-' ( (lv_year_4_0= RULE_INT ) ) ) | ( ( (lv_day_5_0= RULE_INT ) ) otherlv_6= '/' ( (lv_month_7_0= RULE_INT ) ) otherlv_8= '/' ( (lv_year_9_0= RULE_INT ) ) ) ) )
            // InternalScheduling.g:1032:2: ( ( ( (lv_day_0_0= RULE_INT ) ) otherlv_1= '-' ( (lv_month_2_0= RULE_INT ) ) otherlv_3= '-' ( (lv_year_4_0= RULE_INT ) ) ) | ( ( (lv_day_5_0= RULE_INT ) ) otherlv_6= '/' ( (lv_month_7_0= RULE_INT ) ) otherlv_8= '/' ( (lv_year_9_0= RULE_INT ) ) ) )
            {
            // InternalScheduling.g:1032:2: ( ( ( (lv_day_0_0= RULE_INT ) ) otherlv_1= '-' ( (lv_month_2_0= RULE_INT ) ) otherlv_3= '-' ( (lv_year_4_0= RULE_INT ) ) ) | ( ( (lv_day_5_0= RULE_INT ) ) otherlv_6= '/' ( (lv_month_7_0= RULE_INT ) ) otherlv_8= '/' ( (lv_year_9_0= RULE_INT ) ) ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_INT) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==34) ) {
                    alt8=1;
                }
                else if ( (LA8_1==35) ) {
                    alt8=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalScheduling.g:1033:3: ( ( (lv_day_0_0= RULE_INT ) ) otherlv_1= '-' ( (lv_month_2_0= RULE_INT ) ) otherlv_3= '-' ( (lv_year_4_0= RULE_INT ) ) )
                    {
                    // InternalScheduling.g:1033:3: ( ( (lv_day_0_0= RULE_INT ) ) otherlv_1= '-' ( (lv_month_2_0= RULE_INT ) ) otherlv_3= '-' ( (lv_year_4_0= RULE_INT ) ) )
                    // InternalScheduling.g:1034:4: ( (lv_day_0_0= RULE_INT ) ) otherlv_1= '-' ( (lv_month_2_0= RULE_INT ) ) otherlv_3= '-' ( (lv_year_4_0= RULE_INT ) )
                    {
                    // InternalScheduling.g:1034:4: ( (lv_day_0_0= RULE_INT ) )
                    // InternalScheduling.g:1035:5: (lv_day_0_0= RULE_INT )
                    {
                    // InternalScheduling.g:1035:5: (lv_day_0_0= RULE_INT )
                    // InternalScheduling.g:1036:6: lv_day_0_0= RULE_INT
                    {
                    lv_day_0_0=(Token)match(input,RULE_INT,FOLLOW_19); 

                    						newLeafNode(lv_day_0_0, grammarAccess.getDMYDateAccess().getDayINTTerminalRuleCall_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDMYDateRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"day",
                    							lv_day_0_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }

                    otherlv_1=(Token)match(input,34,FOLLOW_10); 

                    				newLeafNode(otherlv_1, grammarAccess.getDMYDateAccess().getHyphenMinusKeyword_0_1());
                    			
                    // InternalScheduling.g:1056:4: ( (lv_month_2_0= RULE_INT ) )
                    // InternalScheduling.g:1057:5: (lv_month_2_0= RULE_INT )
                    {
                    // InternalScheduling.g:1057:5: (lv_month_2_0= RULE_INT )
                    // InternalScheduling.g:1058:6: lv_month_2_0= RULE_INT
                    {
                    lv_month_2_0=(Token)match(input,RULE_INT,FOLLOW_19); 

                    						newLeafNode(lv_month_2_0, grammarAccess.getDMYDateAccess().getMonthINTTerminalRuleCall_0_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDMYDateRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"month",
                    							lv_month_2_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }

                    otherlv_3=(Token)match(input,34,FOLLOW_10); 

                    				newLeafNode(otherlv_3, grammarAccess.getDMYDateAccess().getHyphenMinusKeyword_0_3());
                    			
                    // InternalScheduling.g:1078:4: ( (lv_year_4_0= RULE_INT ) )
                    // InternalScheduling.g:1079:5: (lv_year_4_0= RULE_INT )
                    {
                    // InternalScheduling.g:1079:5: (lv_year_4_0= RULE_INT )
                    // InternalScheduling.g:1080:6: lv_year_4_0= RULE_INT
                    {
                    lv_year_4_0=(Token)match(input,RULE_INT,FOLLOW_2); 

                    						newLeafNode(lv_year_4_0, grammarAccess.getDMYDateAccess().getYearINTTerminalRuleCall_0_4_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDMYDateRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"year",
                    							lv_year_4_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalScheduling.g:1098:3: ( ( (lv_day_5_0= RULE_INT ) ) otherlv_6= '/' ( (lv_month_7_0= RULE_INT ) ) otherlv_8= '/' ( (lv_year_9_0= RULE_INT ) ) )
                    {
                    // InternalScheduling.g:1098:3: ( ( (lv_day_5_0= RULE_INT ) ) otherlv_6= '/' ( (lv_month_7_0= RULE_INT ) ) otherlv_8= '/' ( (lv_year_9_0= RULE_INT ) ) )
                    // InternalScheduling.g:1099:4: ( (lv_day_5_0= RULE_INT ) ) otherlv_6= '/' ( (lv_month_7_0= RULE_INT ) ) otherlv_8= '/' ( (lv_year_9_0= RULE_INT ) )
                    {
                    // InternalScheduling.g:1099:4: ( (lv_day_5_0= RULE_INT ) )
                    // InternalScheduling.g:1100:5: (lv_day_5_0= RULE_INT )
                    {
                    // InternalScheduling.g:1100:5: (lv_day_5_0= RULE_INT )
                    // InternalScheduling.g:1101:6: lv_day_5_0= RULE_INT
                    {
                    lv_day_5_0=(Token)match(input,RULE_INT,FOLLOW_20); 

                    						newLeafNode(lv_day_5_0, grammarAccess.getDMYDateAccess().getDayINTTerminalRuleCall_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDMYDateRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"day",
                    							lv_day_5_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }

                    otherlv_6=(Token)match(input,35,FOLLOW_10); 

                    				newLeafNode(otherlv_6, grammarAccess.getDMYDateAccess().getSolidusKeyword_1_1());
                    			
                    // InternalScheduling.g:1121:4: ( (lv_month_7_0= RULE_INT ) )
                    // InternalScheduling.g:1122:5: (lv_month_7_0= RULE_INT )
                    {
                    // InternalScheduling.g:1122:5: (lv_month_7_0= RULE_INT )
                    // InternalScheduling.g:1123:6: lv_month_7_0= RULE_INT
                    {
                    lv_month_7_0=(Token)match(input,RULE_INT,FOLLOW_20); 

                    						newLeafNode(lv_month_7_0, grammarAccess.getDMYDateAccess().getMonthINTTerminalRuleCall_1_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDMYDateRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"month",
                    							lv_month_7_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }

                    otherlv_8=(Token)match(input,35,FOLLOW_10); 

                    				newLeafNode(otherlv_8, grammarAccess.getDMYDateAccess().getSolidusKeyword_1_3());
                    			
                    // InternalScheduling.g:1143:4: ( (lv_year_9_0= RULE_INT ) )
                    // InternalScheduling.g:1144:5: (lv_year_9_0= RULE_INT )
                    {
                    // InternalScheduling.g:1144:5: (lv_year_9_0= RULE_INT )
                    // InternalScheduling.g:1145:6: lv_year_9_0= RULE_INT
                    {
                    lv_year_9_0=(Token)match(input,RULE_INT,FOLLOW_2); 

                    						newLeafNode(lv_year_9_0, grammarAccess.getDMYDateAccess().getYearINTTerminalRuleCall_1_4_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDMYDateRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"year",
                    							lv_year_9_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDMYDate"

    // Delegated rules


    protected DFA6 dfa6 = new DFA6(this);
    static final String dfa_1s = "\13\uffff";
    static final String dfa_2s = "\1\1\12\uffff";
    static final String dfa_3s = "\1\4\12\uffff";
    static final String dfa_4s = "\1\40\12\uffff";
    static final String dfa_5s = "\1\uffff\1\12\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11";
    static final String dfa_6s = "\1\0\12\uffff}>";
    static final String[] dfa_7s = {
            "\1\1\4\uffff\1\1\1\uffff\1\1\14\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "()+ loopback of 667:6: ( ({...}? => ( ({...}? => (otherlv_2= 'Input:' ( (lv_input_3_0= RULE_STRING ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_4= 'Random_state:' ( (lv_randomState_5_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'Prob_before_restriction:' ( (lv_probBeforeScheduling_7_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'Rate_of_change:' ( (lv_rateOfChange_9_0= RULE_FLOAT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'Days_per_period:' ( (lv_daysPerPeriod_11_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Number_of_periods:' ( (lv_numberOfPeriods_13_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Extra_hours:' ( (lv_extraHours_15_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Iterations:' ( (lv_maxIters_17_0= RULE_INT ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'Start_date:' ( (lv_startDate_19_0= ruleDMYDate ) ) ) ) ) ) )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA6_0 = input.LA(1);

                         
                        int index6_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA6_0==EOF||LA6_0==RULE_INSTALLATIONS_HEADER||LA6_0==RULE_MAINTENANCES_HEADER||LA6_0==RULE_OPTIONS_HEADER) ) {s = 1;}

                        else if ( LA6_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 0) ) {s = 2;}

                        else if ( LA6_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 1) ) {s = 3;}

                        else if ( LA6_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 2) ) {s = 4;}

                        else if ( LA6_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 3) ) {s = 5;}

                        else if ( LA6_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 4) ) {s = 6;}

                        else if ( LA6_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 5) ) {s = 7;}

                        else if ( LA6_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 6) ) {s = 8;}

                        else if ( LA6_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 7) ) {s = 9;}

                        else if ( LA6_0 == 32 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigAccess().getUnorderedGroup_1(), 8) ) {s = 10;}

                         
                        input.seek(index6_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 6, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000A12L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000060002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000FA0000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000FA0002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x00000001FF000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x00000001FF000002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000800000000L});

}
