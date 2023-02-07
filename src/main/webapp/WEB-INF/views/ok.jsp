
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <div class="input-group-text">
                <input type="checkbox" name="accountStrongAuth" id="accountStrongAuth" <c:if test="${account.account_strong_auth==true}">checked=checked</c:if>>
            </div>
        </div>
        <label type="text" class="form-control">Strong authentication</label>
    </div>
    <button type="submit" class="btn btn-primary">Save</button>
</form>