package eyrie

package object syntax {
  object all extends AllSyntax
  object convertible extends ConvertibleSyntax
  object descendant extends DescendantSyntax
  object prefix extends PrefixSyntax
  object partialDescendant extends PartialDescendantSyntax
  object partialSuccessor extends PartialSuccessorSyntax
  object segment extends SegmentSyntax
  object subdivision extends SubdivisionSyntax
  object successor extends SuccessorSyntax
  object suffix extends SuffixSyntax

  object diPartialSuccessor extends DiPartialSuccessorSyntax
  object diSuccessor extends DiSuccessorSyntax
  object equality extends EqualitySyntax
}
