package pl.projectspace.idea.plugins.php.phpspec.core;

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * @author Michal Przytulski <michal@przytulski.pl>
 */
public class PhpSpecLineMarker implements LineMarkerProvider {
	@Nullable
	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull PsiElement element) {
//        if (element instanceof LeafPsiElement && ((LeafPsiElement) element).getElementType().toString().equals("class")) {
//            PsiTreeUtil.getNextSiblingOfType(element, PsiNameIdentifierOwner.class)
//            System.out.println("print 1");
//        }
		return null;
	}

	@Override
	public void collectSlowLineMarkers(@NotNull List<PsiElement> psiElements, @NotNull Collection<LineMarkerInfo> lineMarkerInfos) {
		System.out.println("print 2");
	}
}
