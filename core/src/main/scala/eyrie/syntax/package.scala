package eyrie

package object syntax {
  object all extends AllSyntax
  object convertible extends ConvertibleSyntax
  object descendant extends DescendantSyntax
  object prefix extends PrefixSyntax
  object potentialDescendant extends PotentialDescendantSyntax
  object potentialSuccessor extends PotentialSuccessorSyntax
  object segment extends SegmentSyntax
  object subdivision extends SubdivisionSyntax
  object successor extends SuccessorSyntax
  object suffix extends SuffixSyntax

  object diPotentialSuccessor extends DiPotentialSuccessorSyntax
  object diSuccessor extends DiSuccessorSyntax
  object equality extends EqualitySyntax
}
