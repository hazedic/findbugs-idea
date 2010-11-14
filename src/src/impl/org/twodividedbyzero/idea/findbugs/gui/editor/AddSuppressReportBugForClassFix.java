/*
 * Copyright 2010 Andre Pfeiler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.twodividedbyzero.idea.findbugs.gui.editor;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDeclarationStatement;
import com.intellij.psi.PsiDocCommentOwner;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.twodividedbyzero.idea.findbugs.common.ExtendedProblemDescriptor;


/** todo
 * $Date$
 *
 * @author Andre Pfeiler<andrepdo@dev.java.net>
 * @version $Revision$
 * @since 0.9.97
 */
public class AddSuppressReportBugForClassFix extends AddSuppressReportBugFix {

	public AddSuppressReportBugForClassFix(final String id) {
		super(id);
	}


	public AddSuppressReportBugForClassFix(final ExtendedProblemDescriptor problemDescriptor) {
		super(problemDescriptor);
	}


	@Override
	@Nullable
	protected PsiDocCommentOwner getContainer(final PsiElement element) {
		PsiDocCommentOwner container = super.getContainer(element);
		if (container == null || container instanceof PsiClass) {
			return null;
		}
		while (container != null) {
			final PsiClass parentClass = PsiTreeUtil.getParentOfType(container, PsiClass.class);
			if ((parentClass == null || container.getParent() instanceof PsiDeclarationStatement) && container instanceof PsiClass) {
				return container;
			}
			container = parentClass;
		}
		return container;
	}


	@Override
	@NotNull
	public String getText() {
		return super.getText() + " for class";
	}
}