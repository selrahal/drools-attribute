package com.rhc.drools.example;

import java.util.List;

import org.drools.core.base.ClassFieldReader;
import org.drools.core.definitions.rule.impl.RuleImpl;
import org.drools.core.impl.KnowledgeBaseImpl;
import org.drools.core.rule.GroupElement;
import org.drools.core.rule.Pattern;
import org.drools.core.rule.RuleConditionElement;
import org.drools.core.rule.constraint.MvelConstraint;
import org.drools.core.spi.Constraint;
import org.drools.core.spi.InternalReadAccessor;
import org.kie.api.KieBase;
import org.kie.api.definition.rule.Rule;

import com.rhc.drools.example.kie.KieBaseProvider;

public class Attributes {
	public static void main(String[] args) {
		System.out.println("Starting... ");
		KieBaseProvider kbp = new KieBaseProvider();
		KieBase kieBase = kbp.getKieBaseForDecisionTable();
		System.out.println("Using " + kieBase);
		KnowledgeBaseImpl knowledgeBaseImpl = (KnowledgeBaseImpl) kieBase;
		Rule salsAge = knowledgeBaseImpl.getRule("com.rhc.drools.example", "Sal's age");
		RuleImpl salsAgeImpl = (RuleImpl) salsAge;
		GroupElement leftHandConditions = salsAgeImpl.getLhs();
		printGroupElement("", leftHandConditions);
	}
	
	private static void printGroupElement(String prefix, GroupElement groupElement) {
		System.out.println(prefix + "Type '" + groupElement.getType() + "'");
		printRuleConditionElements(prefix + "-", groupElement.getChildren());
	}
	
	private static void printRuleConditionElements(String prefix, List<RuleConditionElement> ruleConditionElements) {
		for (RuleConditionElement ruleConditionElement : ruleConditionElements) {
			if (ruleConditionElement instanceof Pattern) {
				printPattern(prefix,(Pattern) ruleConditionElement);
			} else if (ruleConditionElement instanceof GroupElement) {
				printGroupElement(prefix, (GroupElement)ruleConditionElement);
			} else {
				System.err.println(prefix + "Not currently supporting RuleConditionElement type " + ruleConditionElement.getClass());
			}
		}
	}
	
	private static void printPattern(String prefix, Pattern pattern) {
		for (Constraint constraint : pattern.getConstraints()) {
			if (constraint instanceof MvelConstraint) {
				MvelConstraint mvelConstraint = (MvelConstraint) constraint;
				InternalReadAccessor internalReadAccessor = mvelConstraint.getFieldExtractor();
				if (internalReadAccessor instanceof ClassFieldReader) {
					ClassFieldReader classFieldReader = (ClassFieldReader)internalReadAccessor;
					System.out.println(prefix + "On class '" + classFieldReader.getClassName() + "' using field '" + classFieldReader.getFieldName() + "'");
				} else {
					System.err.println(prefix + "Not currently supporting InternalReadAccesor type " + internalReadAccessor.getClass());
				}
			} else {
				System.err.println(prefix + "Not currently supporting Constraint type " + constraint.getClass());
			}
		}
	}

}
